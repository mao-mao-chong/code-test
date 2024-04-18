package org.com.bmw.service.impl;

import org.com.bmw.dao.SaleDataDao;
import org.com.bmw.model.Product;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.model.SaleData;
import org.com.bmw.service.SaleDataService;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleDataServiceImpl implements SaleDataService {
    @Autowired
    SaleDataDao saleDataDao;
    @Override
    public ReturnMsg querySaleDataList(SaleData saleData, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        List<SaleData> saleDataResult = saleDataDao.querySaleDataList(saleData,commonQueryBean);
        //查询总条数
        int count = saleDataDao.count(saleData);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(saleDataResult);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    @Override
    public ReturnMsg insertSaleData(SaleData saleData) {
        return null;
    }
}
