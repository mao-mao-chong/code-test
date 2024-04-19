package org.com.bmw.service.impl;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.com.bmw.dao.ProductOrderDao;
import org.com.bmw.dao.SaleDataDao;
import org.com.bmw.model.*;
import org.com.bmw.service.SaleDataService;
import org.com.bmw.util.AdminUserUtil;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class SaleDataServiceImpl implements SaleDataService {
    @Autowired
    SaleDataDao saleDataDao;
    @Autowired
    ProductOrderDao productOrderDao;
    @Override
    public ReturnMsg querySaleDataList(SaleData saleData, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        AdminUser user = AdminUserUtil.getLoginUser();
        saleData.setStoreId(user.getStoreId());
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
    public void insertSaleData() {
        //获取当天所有商户下，每个产品的销量额
        List<ProductOrder> productOrderList = productOrderDao.queryProductOrderAmount();
        List<SaleData> saleDataList = new ArrayList<SaleData>();
        if(CollectionUtils.isEmpty(productOrderList)){
            return ;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH , -1);
        Date yesterday = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            yesterday = sdf.parse(sdf.format(yesterday)) ;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        for(ProductOrder order :productOrderList){
            SaleData saleData = new SaleData();
            saleData.setStoreId(order.getStoreId());
            saleData.setSaleAmount(order.getOrderAmount());
            saleData.setProductId(order.getProductId());
            saleData.setProductName(order.getProductName());
            saleData.setSaleDate(yesterday);
            saleDataList.add(saleData);
        }
        saleDataDao.insertSaleDataBatch(saleDataList);
    }
}
