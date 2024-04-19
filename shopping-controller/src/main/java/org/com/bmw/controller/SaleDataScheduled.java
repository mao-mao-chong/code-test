package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.service.SaleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Slf4j
public class SaleDataScheduled {

    @Autowired
    SaleDataService saleDataService;
    @Scheduled(cron = "0 0 1 * * ?")
    public void delSaleData() {
        log.info("开始跑批销售数据，当前时间:{}",new Date());
        saleDataService.insertSaleData();
        log.info("跑批销售数据结束，当前时间:{}",new Date());
    }

}
