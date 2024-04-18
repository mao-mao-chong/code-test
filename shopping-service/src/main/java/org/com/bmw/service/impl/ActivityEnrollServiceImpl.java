package org.com.bmw.service.impl;

import org.com.bmw.dao.ActivityDao;
import org.com.bmw.dao.ActivityEnrollDao;
import org.com.bmw.dao.ProductDao;
import org.com.bmw.dao.StoreDao;
import org.com.bmw.model.*;
import org.com.bmw.service.ActivityEnrollService;
import org.com.bmw.util.AdminUserUtil;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityEnrollServiceImpl implements ActivityEnrollService {
    @Autowired
    ActivityEnrollDao activityEnrollDao;
    @Autowired
    ActivityDao activityDao;
    @Autowired
    StoreDao storeDao;
    @Autowired
    ProductDao productDao;
    @Override
    public ReturnMsg queryActivityEnroll(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ActivityEnroll activityEnrollResult = activityEnrollDao.queryActivityEnroll(activityEnroll);
        returnMsg.setData(activityEnrollResult);
        return returnMsg;
    }

    @Override
    public ReturnMsg queryActivityEnrollList(ActivityEnroll activityEnroll, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        List<ActivityEnroll> list = activityEnrollDao.queryActivityEnrollList(activityEnroll,commonQueryBean);
        int count = activityEnrollDao.count(activityEnroll);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(list);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    @Override
    public ReturnMsg enroll(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        //活动名称获取
        Activity activity = new Activity();
        activity.setId(activityEnroll.getActivityId());
        activity = activityDao.queryActivity(activity);
        activityEnroll.setActivityName(activity.getActivityName());
        //店铺信息获取
        AdminUser user = AdminUserUtil.getLoginUser();
        Store store = new Store();
        store.setId(user.getStoreId());
        store = storeDao.selectExistStore(store);
        activityEnroll.setStoreName(store.getStoreName());
        activityEnroll.setStoreId(store.getId());
        //产品信息获取
        Product product = new Product();
        product.setId(activityEnroll.getProductId());
        product = productDao.queryProduct(product);
        activityEnroll.setProductName(product.getProductName());
        activityEnrollDao.insertActivityEnroll(activityEnroll);
        return returnMsg;
    }

    @Override
    public ReturnMsg cancelEnroll(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        activityEnroll.setDelFlag(1);
        activityEnrollDao.modifyActivityEnroll(activityEnroll);
        return returnMsg;
    }

}
