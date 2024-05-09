package org.com.bmw.controller;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.Product;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductService;
import org.com.bmw.util.Constant;
import org.com.bmw.util.RedisUtil;
import org.com.bmw.vo.ProductOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 数据统计类
 */
@RestController
@RequestMapping(value = "/statistics")
@Slf4j
public class StatisticsController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    ProductService productService;
    //获取优质产品-根据产品类型
    //获取每个产品类型下的销量前十的产品-REDIS ZSET
    @RequestMapping(value = "/queryHighQualityProduct", method = RequestMethod.POST)
    public ReturnMsg queryHighQualityProduct(@RequestBody @Validated ProductOrderVO productOrderVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        //获取前10名的产品id
        Set<Object> productIds = redisUtil.range(productOrderVO.getProductTypeId().toString(),0,9);
        List<Object> productIdList = new ArrayList<Object>(productIds);
        returnMsg = productService.queryProductByProductIdList((List<Long>)(List<?>)productIdList);
        Map<Integer, Product> map= dealRank(productIdList,(List<Product>)returnMsg.getData());
        returnMsg.setData(map);
        return returnMsg;
    }
    //系统启动时，给所有商品添加标签，用来用户搜索时推荐使用 -REDIS SET

    //string\hash--登录存储


    //list-待定
    private Map<Integer, Product> dealRank(List<Object> ids,List<Product> list){
        Map<Integer, Product> map = new HashMap<Integer, Product>();
        for(int i=0;i<ids.size();i++){
            for(Product product : list){
                if(StringUtils.equals(product.getId().toString(),ids.get(i).toString())){
                    map.put(ids.size()-i,product);
                }
            }
        }
        return map;
    }
}
