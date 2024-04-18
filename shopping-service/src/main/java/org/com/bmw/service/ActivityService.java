package org.com.bmw.service;

import org.com.bmw.model.Activity;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.CommonQueryBean;

import java.util.List;

public interface ActivityService {
    /**
     * 活动查询
     * @param activity
     * @return
     */
    ReturnMsg queryActivity(Activity activity);

    /**
     * 活动列表
     * @param activity
     * @param commonQueryBean
     * @return
     */
    ReturnMsg queryActivityList(Activity activity, CommonQueryBean commonQueryBean);

    /**
     * 活动新增
     */
    ReturnMsg insertActivity(Activity activity);
    /**
     * 活动修改
     */
    ReturnMsg modifyActivity(Activity activity);
    /**
     * 活动删除
     */
    ReturnMsg delActivity(Activity activity);


}
