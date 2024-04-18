package org.com.bmw.service.impl;

import org.com.bmw.dao.ProductTypeDao;
import org.com.bmw.model.Activity;
import org.com.bmw.model.Product;
import org.com.bmw.model.ProductType;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductTypeService;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeDao productTypeDao;
    @Override
    public ReturnMsg queryProductType(ProductType productType) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ProductType productTypeResult = productTypeDao.queryProductType(productType);
        returnMsg.setData(productTypeResult);
        return returnMsg;
    }

    @Override
    public ReturnMsg queryProductTypeList(ProductType productType, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        List<ProductType> productTypeResult = productTypeDao.queryProductTypeList(productType,commonQueryBean);
        //查询总条数
        int count = productTypeDao.count(productType);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(productTypeResult);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    @Override
    public ReturnMsg insertProductType(ProductType productType) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        productTypeDao.insertProductType(productType);
        return returnMsg;
    }

    @Override
    public ReturnMsg modifyProductType(ProductType productType) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        productTypeDao.modifyProductType(productType);
        return returnMsg;
    }

    @Override
    public ReturnMsg delProductType(ProductType productType) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ProductType productTypeDel = new ProductType();
        productTypeDel.setId(productType.getId());
        productTypeDel.setDelFlag(1);
        productTypeDao.modifyProductType(productTypeDel);
        return returnMsg;
    }
}
