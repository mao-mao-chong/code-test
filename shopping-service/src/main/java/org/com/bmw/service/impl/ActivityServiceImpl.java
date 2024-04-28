package org.com.bmw.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.dao.ActivityDao;
import org.com.bmw.model.Activity;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ActivityService;
import org.com.bmw.util.BusinessUtils;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;
    @Override
    public ReturnMsg queryActivity(Activity activity) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        Activity activityResult = activityDao.queryActivity(activity);
        returnMsg.setData(activityResult);
        return returnMsg;
    }

    @Override
    public ReturnMsg queryActivityList(Activity activity, CommonQueryBean commonQueryBean) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        if(commonQueryBean!=null && commonQueryBean.getPageNum()!=null && commonQueryBean.getPageSize()!=null){
            commonQueryBean.setStart((commonQueryBean.getPageNum()-1)*commonQueryBean.getPageSize());
        }
        long start = System.currentTimeMillis();
        List<Activity> activityResult = activityDao.queryActivityList(activity,commonQueryBean);
        long end = System.currentTimeMillis();
        log.info("查询活动列表耗时：{}",(end-start));
        //查询总条数
        int count = activityDao.count(activity);
        commonQueryBean.setTotal(count);
        commonQueryBean.setTotalPage(BusinessUtils.calculateTotalPage(commonQueryBean));
        returnMsg.setData(activityResult);
        returnMsg.setCommonQueryBean(commonQueryBean);
        return returnMsg;
    }

    @Override
    public ReturnMsg insertActivity(Activity activity) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        activityDao.insertActivity(activity);
        return returnMsg;
    }

    @Override
    public ReturnMsg modifyActivity(Activity activity) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        activityDao.modifyActivity(activity);
        return returnMsg;
    }

    @Override
    public ReturnMsg delActivity(Activity activity) {
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        Activity activityDel = new Activity();
        activityDel.setId(activity.getId());
        activityDel.setDelFlag(1);
        activityDao.modifyActivity(activityDel);
        return returnMsg;
    }
}
