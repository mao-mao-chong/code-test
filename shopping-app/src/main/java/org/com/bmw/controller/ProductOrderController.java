package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.Activity;
import org.com.bmw.model.ProductOrder;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductOrderService;
import org.com.bmw.util.Constant;
import org.com.bmw.vo.ProductOrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单类
 */
@RestController
@RequestMapping(value = "/productOrder")
@Slf4j
public class ProductOrderController {
    @Autowired
    ProductOrderService productOrderService;
    //下单，更新redis
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ReturnMsg order(@RequestBody ProductOrderVO productOrderVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ProductOrder productOrder = new ProductOrder();
        BeanUtils.copyProperties(productOrderVO,productOrder);
        returnMsg = productOrderService.order(productOrder);
        return returnMsg;
    }
}
