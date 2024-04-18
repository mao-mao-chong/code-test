package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.Activity;
import org.com.bmw.model.ActivityEnroll;
import org.com.bmw.util.CommonQueryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityEnrollDao {
    /**
     * 报名的活动查询
     * @param activityEnroll
     * @return
     */
    ActivityEnroll queryActivityEnroll(ActivityEnroll activityEnroll);

    /**
     * 报名的活动列表
     * @param activityEnroll
     * @param commonQueryBean
     * @return
     */
    List<ActivityEnroll> queryActivityEnrollList(@Param("activityEnroll") ActivityEnroll activityEnroll, @Param("commonQueryBean") CommonQueryBean commonQueryBean);
    /**
     * 报名活动
     */
    int insertActivityEnroll(ActivityEnroll activityEnroll);
    /**
     * 取消报名
     */
    int modifyActivityEnroll(ActivityEnroll activityEnroll);

    int count(ActivityEnroll activityEnroll);
}
