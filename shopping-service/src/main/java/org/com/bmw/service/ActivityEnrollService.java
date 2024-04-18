package org.com.bmw.service;

import org.com.bmw.model.Activity;
import org.com.bmw.model.ActivityEnroll;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.CommonQueryBean;

public interface ActivityEnrollService {
    /**
     * 报名的活动查询
     * @param activityEnroll
     * @return
     */
    ReturnMsg queryActivityEnroll(ActivityEnroll activityEnroll);

    /**
     * 报名的活动列表
     * @param activityEnroll
     * @param commonQueryBean
     * @return
     */
    ReturnMsg queryActivityEnrollList(ActivityEnroll activityEnroll, CommonQueryBean commonQueryBean);

    /**
     *报名活动
     *
     */
    ReturnMsg enroll(ActivityEnroll activityEnroll);

    /**
     * 取消报名
     * @param activityEnroll
     * @return
     */
    ReturnMsg cancelEnroll(ActivityEnroll activityEnroll);
}
