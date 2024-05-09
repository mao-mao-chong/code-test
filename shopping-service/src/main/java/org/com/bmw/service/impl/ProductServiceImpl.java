package org.com.bmw.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.com.bmw.dao.ProductDao;
import org.com.bmw.model.Activity;
import org.com.bmw.model.AdminUser;
import org.com.bmw.model.Product;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductService;
import org.com.bmw.util.AdminUserUtil;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public ReturnMsg queryProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        product.setStoreId(user.getStoreId());
        Product productResult = productDao.queryProduct(product);
        returnMsg.setData(productResult);
        return returnMsg;
    }

    @Override
    public ReturnMsg queryProductList(Product product, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        AdminUser user = AdminUserUtil.getLoginUser();
        long  start = System.currentTimeMillis();
        List<Product> productResult = productDao.queryProductList(product,commonQueryBean);
        long  end = System.currentTimeMillis();
        log.info("获取产品列表耗时：{}",(end-start));
        //查询总条数
        int count = productDao.count(product);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(productResult);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    @Override
    public ReturnMsg insertProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        product.setStoreId(user.getStoreId());
        productDao.insertProduct(product);
        return returnMsg;
    }

    @Override
    public ReturnMsg modifyProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        //查看是否本商户产品
        product.setStoreId(user.getStoreId());
        Product existProduct = productDao.queryProduct(product);
        if(existProduct==null){
            return new ReturnMsg(Constant.PRODUCT_NOT_EXIST.getCode(),Constant.PRODUCT_NOT_EXIST.getMessage());
        }
        productDao.modifyProduct(product);
        return returnMsg;
    }

    @Override
    public ReturnMsg delProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        Product productDel = new Product();
        productDel.setId(product.getId());
        productDel.setStoreId(user.getStoreId());
        Product existProduct = productDao.queryProduct(productDel);
        if(existProduct==null){
            return new ReturnMsg(Constant.PRODUCT_NOT_EXIST.getCode(),Constant.PRODUCT_NOT_EXIST.getMessage());
        }
        productDel.setDelFlag(1);
        productDao.modifyProduct(productDel);
        return returnMsg;
    }

    @Override
    public ReturnMsg queryProductByProductIdList(List<Long> list) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        List<Product> productList = productDao.queryProductByProductIdList(list);
        returnMsg.setData(productList);
        return returnMsg;
    }

    @Override
    public Map<Long, List<Object>> queryProductList() {
        List<Product> list = productDao.queryProductList(null,null);
        return dealProductListToMap(list);
    }

    private Map<Long, List<Object>> dealProductListToMap(List<Product> list){
        Map<Long, List<Object>> map = new HashedMap();
        for(Product product:list){
            if(map.get(product.getProductTypeId())!=null){
                map.get(product.getProductTypeId()).add(product);
            }else{
                List<Object> oneProductList = new ArrayList<Object>();
                oneProductList.add(product);
                map.put(product.getProductTypeId(),oneProductList);
            }
        }
        return map;
    }
}
