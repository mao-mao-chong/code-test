package org.com.bmw.service.impl;

import org.com.bmw.dao.ProductDao;
import org.com.bmw.model.Activity;
import org.com.bmw.model.AdminUser;
import org.com.bmw.model.Product;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductService;
import org.com.bmw.util.AdminUserUtil;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public ReturnMsg queryProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        Product productResult = productDao.queryProduct(product);
        returnMsg.setData(productResult);
        return returnMsg;
    }

    @Override
    public ReturnMsg queryProductList(Product product, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        List<Product> productResult = productDao.queryProductList(product,commonQueryBean);
        //查询总条数
        int count = productDao.count(product);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(productResult);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    @Override
    public ReturnMsg insertProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        product.setStoreId(user.getStoreId());
        productDao.insertProduct(product);
        return returnMsg;
    }

    @Override
    public ReturnMsg modifyProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        productDao.modifyProduct(product);
        return returnMsg;
    }

    @Override
    public ReturnMsg delProduct(Product product) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        Product productDel = new Product();
        productDel.setId(product.getId());
        productDel.setDelFlag(1);
        productDao.modifyProduct(productDel);
        return returnMsg;
    }
}
