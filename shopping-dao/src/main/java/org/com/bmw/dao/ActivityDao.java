package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.Activity;
import org.com.bmw.model.ActivityEnroll;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.CommonQueryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao {
    /**
     * 活动查询
     * @param activity
     * @return
     */
    Activity queryActivity(Activity activity);

    /**
     * 活动列表
     * @param activity
     * @param commonQueryBean
     * @return
     */
    List<Activity> queryActivityList(@Param("activity") Activity activity, @Param("commonQueryBean")CommonQueryBean commonQueryBean);

    /**
     * 活动新增
     */
    int insertActivity(Activity activity);
    /**
     * 活动修改(包含逻辑删除)
     */
    int modifyActivity(Activity activity);

    int count(Activity activity);
}
