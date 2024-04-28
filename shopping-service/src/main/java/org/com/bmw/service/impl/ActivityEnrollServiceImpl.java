package org.com.bmw.service.impl;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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
        AdminUser user = AdminUserUtil.getLoginUser();
        activityEnroll.setStoreId(user.getStoreId());//确保查询本商户下报名的活动
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
        AdminUser user = AdminUserUtil.getLoginUser();
        activityEnroll.setStoreId(user.getStoreId());//确保查询本商户下报名的活动
        List<ActivityEnroll> list = activityEnrollDao.queryActivityEnrollList(activityEnroll,commonQueryBean);
        int count = activityEnrollDao.count(activityEnroll);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(list);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    /**
     *      * 活动报名表只保存了产品id、活动id，其他数据通过其他表关联查询
     *      *根据活动id,产品id查询产品信息，活动信息，店铺信息
     *      * @param activityEnroll
     *      * @param commonQueryBean
     *      * @return
     */


    @Override
    public ReturnMsg enroll(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        //活动名称获取
        Activity activity = new Activity();
        activity.setId(activityEnroll.getActivityId());
        activity = activityDao.queryActivity(activity);
        if(activity.getActivityName()!=null){
            returnMsg = new ReturnMsg(Constant.ACTIVITY_NOT_EXIST.getCode(),Constant.ACTIVITY_NOT_EXIST.getMessage());
            return returnMsg;
        }
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
        product.setStoreId(user.getStoreId());
        product = productDao.queryProduct(product);
        if(product.getProductName()!=null){
            returnMsg = new ReturnMsg(Constant.PRODUCT_NOT_EXIST.getCode(),Constant.PRODUCT_NOT_EXIST.getMessage());
            return returnMsg;
        }
        activityEnroll.setProductName(product.getProductName());
        activityEnrollDao.insertActivityEnroll(activityEnroll);
        return returnMsg;
    }

    @Override
    public ReturnMsg cancelEnroll(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        activityEnroll.setStoreId(user.getStoreId());
        ActivityEnroll existActivityEnroll = activityEnrollDao.queryActivityEnroll(activityEnroll);
        if(existActivityEnroll==null){
            return new ReturnMsg(Constant.ACTIVITY_ENROLL_NOT_EXIST.getCode(),Constant.ACTIVITY_ENROLL_NOT_EXIST.getMessage());
        }
        activityEnroll.setDelFlag(1);
        activityEnrollDao.modifyActivityEnroll(activityEnroll);
        return returnMsg;
    }




    @Override
    public ReturnMsg queryActivityEnrollListForExampleJoin(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());

        AdminUser user = AdminUserUtil.getLoginUser();
        /**
         *  1.大sql联查
         */
        activityEnroll.setStoreId(user.getStoreId());
        log.info("查询入参：{}",activityEnroll);
        long joinStart = System.currentTimeMillis();
        List<ActivityEnroll> joinList = activityEnrollDao.queryActivityEnrollListExample(activityEnroll);
        long joinEnd = System.currentTimeMillis();
        log.info("多表联查---总耗时：{}",joinEnd-joinStart);
        log.info("活动查询总数据：{}",joinList.size());

        returnMsg.setData("多表联查总耗时="+(joinEnd-joinStart));
        return returnMsg;
    }
    @Override
    public ReturnMsg queryActivityEnrollListForExampleFen(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        AdminUser user = AdminUserUtil.getLoginUser();
        activityEnroll.setStoreId(user.getStoreId());
        //2.分表查询
        long fenStart = System.currentTimeMillis();
        //获取店铺信息
        Store store = new Store();
        store.setId(user.getStoreId());
        store = storeDao.selectExistStore(store);
        //获取产品信息
        long proStart = System.currentTimeMillis();
        Product product = new Product();
        product.setStoreId(user.getStoreId());
        product.setId(activityEnroll.getProductId());
        product = productDao.queryProduct(product);
        long proEnd = System.currentTimeMillis();
        log.info("query product use:{}",(proEnd-proStart));
        //获取活动信息
        long acStart = System.currentTimeMillis();
        Activity activity = new Activity();
        activity.setId(activityEnroll.getActivityId());
        activity = activityDao.queryActivity(activity);
        long acEnd = System.currentTimeMillis();
        log.info("query activity use:{}",(acEnd-acStart));
        //查询报名活动并组装
        long enStart = System.currentTimeMillis();
        List<ActivityEnroll> fenList = activityEnrollDao.queryActivityEnrollListFen(activityEnroll);
        long enEnd = System.currentTimeMillis();
        log.info("query activity use:{}",(enEnd-enStart));
        long fenEnd = System.currentTimeMillis();
        log.info("分表查询信息---总耗时：{}",fenEnd-fenStart);
        log.info("活动查询总数据：{}",fenList.size());

        returnMsg.setData("分表查询信息总耗时="+(fenEnd-fenStart));
        return returnMsg;
    }
    @Override
    public ReturnMsg queryActivityEnrollListForExampleMore(ActivityEnroll activityEnroll) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());

        AdminUser user = AdminUserUtil.getLoginUser();
        /**
         * 冗余字段设计
         */
        activityEnroll.setStoreId(user.getStoreId());//确保查询本商户下报名的活动
        long moreStart = System.currentTimeMillis();
        List<ActivityEnroll> list = activityEnrollDao.queryActivityEnrollListMore(activityEnroll);
        long moreEnd = System.currentTimeMillis();
        log.info("冗余字段设计---总耗时：{}",moreEnd-moreStart);
        int count = activityEnrollDao.count(activityEnroll);
        returnMsg.setData("冗余字段设计总耗时="+(moreEnd-moreStart));
        log.info("活动查询总数据：{}",list.size());

        return returnMsg;
    }

    @Override
    public ReturnMsg queryActivityEnrollListByProductOrActivity(ActivityEnroll activityEnroll, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        AdminUser user = AdminUserUtil.getLoginUser();
        activityEnroll.setStoreId(user.getStoreId());//确保查询本商户下报名的活动
        long start = System.currentTimeMillis();
        List<ActivityEnroll> list = activityEnrollDao.queryActivityEnrollListByProductOrActivity(activityEnroll,commonQueryBean);
        long end = System.currentTimeMillis();
        log.info("活动查询耗时-OR测试：{}",(end-start));
        //TODO 分页暂未处理
        returnMsg.setData(list);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

}
