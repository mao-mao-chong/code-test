package org.com.bmw.service;

import org.com.bmw.model.Activity;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.model.SaleData;
import org.com.bmw.util.CommonQueryBean;
import org.springframework.stereotype.Service;


public interface SaleDataService {
    /**
     * 销售数据查询
     * @param saleData
     * @return
     */
    ReturnMsg querySaleDataList(SaleData saleData,CommonQueryBean commonQueryBean);



    /**
     * 销售数据新增
     */
    void insertSaleData();

}
