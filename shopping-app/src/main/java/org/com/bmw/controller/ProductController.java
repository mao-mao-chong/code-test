package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.com.bmw.model.AdminUser;
import org.com.bmw.model.Product;
import org.com.bmw.model.ProductType;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductService;
import org.com.bmw.service.ProductTypeService;
import org.com.bmw.util.*;
import org.com.bmw.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/app/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${productDescList.snacks}")
    private String snacks;
    @Value("${productDescList.clothing}")
    private String clothing;
    @Autowired
    private ProductTypeService productTypeService;
    //商品列表查询
    @RequestMapping(value = "/queryProductList", method = RequestMethod.POST)
    public ReturnMsg queryProductList(@RequestBody ProductVO productVO){
        log.info("商品列表查询：{}"+productVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Product product = new Product();
            CommonQueryBean commonQueryBean = new CommonQueryBean();
            BeanUtils.copyProperties(productVO,product);
            BeanUtils.copyProperties(productVO,commonQueryBean);
            returnMsg = productService.queryProductList(product,commonQueryBean);
            //如果搜索到产品，则记录登录用户搜索的产品的产品类型，同于用户喜欢推荐
            AdminUser user = AdminUserUtil.getLoginUser();
            List<Product> list = (ArrayList<Product>)returnMsg.getData();
            if(CollectionUtils.isNotEmpty(list) && user!=null){
                List<Product> productResult = (List<Product>)returnMsg.getData();
                redisUtil.sSet(user.getId().toString()+user.getUsername(),productResult.get(0).getProductTypeId());
            }
            //如果搜索到的结果为空，进行同类型产品推荐,此处模拟匹配搜索产品的品类
            if(CollectionUtils.isEmpty(list)){
                String productTypeId = checkProductTypeId(productVO.getProductName());
                returnMsg = getRedisProductList(productTypeId,commonQueryBean);
                list = (ArrayList<Product>)returnMsg.getData();
            }
            //如果以上数据都未匹配到数据，进行用户喜欢推荐//当用户再次搜索并且没搜索到时，进行推荐之前搜索的产品类型的产品，每次推荐一个类型
            if(CollectionUtils.isEmpty(list) && user!=null){
                Set<Object> productTypeIdSet = redisUtil.sGet(user.getId().toString()+user.getUsername());
                if(productTypeIdSet!=null){
                    String productTypeId = productTypeIdSet.iterator().next().toString();
                    returnMsg = getRedisProductList(productTypeId,commonQueryBean);
                }

            }
        }catch(Exception e){
            log.info("商品列表查询发生异常：{}",e);
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }

    private String checkProductTypeId(String productName){
        String productTypeId ="";
        if(StringUtils.isEmpty(productName)){
            return Constant.DEFAULT_PRODUCT_TYPE_ID.getMessage();//默认推荐
        }
        if(StringUtils.contains(snacks,productName)){
            productTypeId = getProductTypeId(Constant.SNACKS.getMessage());
        }else if(StringUtils.contains(clothing,productName)){
            productTypeId = getProductTypeId(Constant.CLOTHING.getMessage());
        }
        return productTypeId;
    }
    private String getProductTypeId(String productTypeName){
        ProductType productType = new ProductType();
        productType.setProductTypeName(productTypeName);
        ReturnMsg returnMsg = productTypeService.queryProductType(productType);
        if(returnMsg.getData()==null){
            return Constant.DEFAULT_PRODUCT_TYPE_ID.getMessage();//默认推荐
        }
        productType = (ProductType)returnMsg.getData();
        return productType.getId().toString();
    }

    private ReturnMsg getRedisProductList(String productTypeId,CommonQueryBean commonQueryBean){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        long total = redisUtil.lGetListSize(productTypeId);
        List<Product> productList = (List<Product>)(List<?>)redisUtil.lGet(productTypeId,commonQueryBean.getStart(),commonQueryBean.getStart()+commonQueryBean.getPageSize());
        commonQueryBean.setTotal((int) total);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(productList);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }
}
