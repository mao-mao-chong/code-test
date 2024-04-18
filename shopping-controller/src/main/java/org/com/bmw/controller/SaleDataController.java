package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.Activity;
import org.com.bmw.model.ProductType;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.model.SaleData;
import org.com.bmw.service.SaleDataService;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.com.bmw.vo.SaleDataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/saleData")
@Slf4j
public class SaleDataController {
    @Autowired
    SaleDataService saleDataService;


    //销售数据查询
    @RequestMapping(value = "/querySaleDataList", method = RequestMethod.POST)
    public ReturnMsg querySaleDataList(SaleDataVO saleDataVO){
        log.info("销售数据查询:{}"+saleDataVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            SaleData saleData = new SaleData();
            CommonQueryBean commonQueryBean = new CommonQueryBean();
            BeanUtils.copyProperties(saleDataVO,saleData);
            BeanUtils.copyProperties(saleDataVO,commonQueryBean);
            returnMsg = saleDataService.querySaleDataList(saleData,commonQueryBean);
        }catch(Exception e){
            log.info("销售数据查询异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
}
