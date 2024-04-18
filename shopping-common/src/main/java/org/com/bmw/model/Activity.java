package org.com.bmw.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity extends BaseModel {
    //活动名称
    private String activityName;
    //活动开始时间
    private Date activityStartTime;
    //活动结束时间
    private Date activityEndTime;
    //非数据库维护字段
    //活动开始时间
    private String activityStartTimeString;
    //活动结束时间
    private String activityEndTimeString;
    public Date getActivityStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(activityStartTimeString)) {
                this.activityStartTime = sdf.parse(activityStartTimeString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return activityStartTime;
    }

    public void setActivityStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(activityStartTimeString)) {
                this.activityStartTime = sdf.parse(activityStartTimeString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getActivityEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(activityEndTimeString)){
                activityEndTime = sdf.parse(activityEndTimeString);
            }
            return activityEndTime;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void setActivityEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(activityEndTimeString)){
                this.activityEndTime = sdf.parse(activityEndTimeString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityStartTimeString() {
        return activityStartTimeString;
    }

    public void setActivityStartTimeString(String activityStartTimeString) {
        this.activityStartTimeString = activityStartTimeString;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityName='" + activityName + '\'' +
                ", activityStartTime=" + activityStartTime +
                ", activityEndTime=" + activityEndTime +
                ", activityStartTimeString='" + activityStartTimeString + '\'' +
                ", activityEndTimeString='" + activityEndTimeString + '\'' +
                '}';
    }

    public String getActivityEndTimeString() {
        return activityEndTimeString;
    }

    public void setActivityEndTimeString(String activityEndTimeString) {
        this.activityEndTimeString = activityEndTimeString;
    }

}
