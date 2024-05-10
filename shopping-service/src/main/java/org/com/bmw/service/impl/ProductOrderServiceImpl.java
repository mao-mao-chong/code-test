package org.com.bmw.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.dao.ProductOrderDao;
import org.com.bmw.model.ProductOrder;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductOrderService;
import org.com.bmw.util.Constant;
import org.com.bmw.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductOrderServiceImpl implements ProductOrderService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    ProductOrderDao productOrderDao;
    @Override
    public ReturnMsg order(ProductOrder productOrder) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        //1.下单
        productOrderDao.insertProductOrder(productOrder);
        //2.更新REDIS该产品产品类型下产品的订单数量
        redisUtil.incrementScore(productOrder.getProductTypeId().toString(),productOrder.getProductId().toString(),
                productOrder.getProductCount().doubleValue());
        return returnMsg;
    }
}
