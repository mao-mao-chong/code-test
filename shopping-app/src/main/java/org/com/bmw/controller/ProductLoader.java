package org.com.bmw.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.com.bmw.model.Product;
import org.com.bmw.service.ProductService;
import org.com.bmw.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Order(1)
public class ProductLoader implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    ProductService productService;
    //推荐
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        //用于当用户查询产品时，未查询到数据，推荐所搜索的产品对应的产品类型，将产品放入redis-list中，在产品查询时使用，可分页
        //将不同产品类型的产品查回来，放入产品放倒redis-list里，用于分页
        Map<Long, List<Object>> map = productService.queryProductList();
        for (Map.Entry<Long, List<Object>> entry : map.entrySet()) {
            List<Object> list= entry.getValue();
            redisUtil.del(entry.getKey().toString());
            redisUtil.lSet(entry.getKey().toString(),list);

        }
    }
    //用户行为分析推荐
}
