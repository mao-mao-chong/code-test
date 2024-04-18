package org.com.bmw.controller;

import org.com.bmw.model.Activity;
import org.com.bmw.model.ActivityEnroll;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ActivityEnrollService;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.com.bmw.vo.ActivityEnrollVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/activityEnroll")
public class ActivityEnrollController {

    @Autowired
    ActivityEnrollService activityEnrollService;
    // 活动报名
    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public ReturnMsg enroll(@RequestBody ActivityEnrollVO activityEnrollVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ActivityEnroll activityEnroll = new ActivityEnroll();
        BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
        returnMsg = activityEnrollService.enroll(activityEnroll);
        return returnMsg;
    }
    // 取消报名
    @RequestMapping(value = "/cancelEnroll", method = RequestMethod.POST)
    public ReturnMsg cancelEnroll(@RequestBody ActivityEnrollVO activityEnrollVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ActivityEnroll activityEnroll = new ActivityEnroll();
        BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
        returnMsg = activityEnrollService.cancelEnroll(activityEnroll);
        return returnMsg;
    }
    // 报名活动列表查询
    @RequestMapping(value = "/queryActivityEnrollList", method = RequestMethod.POST)
    public ReturnMsg activityEnrollList(@RequestBody ActivityEnrollVO activityEnrollVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ActivityEnroll activityEnroll = new ActivityEnroll();
        CommonQueryBean commonQueryBean = new CommonQueryBean();
        BeanUtils.copyProperties(activityEnrollVO,commonQueryBean);
        BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
        returnMsg = activityEnrollService.queryActivityEnrollList(activityEnroll,commonQueryBean);
        return returnMsg;
    }
    // 报名活动列表查询
    @RequestMapping(value = "/queryActivityEnroll", method = RequestMethod.POST)
    public ReturnMsg queryActivityEnroll(@RequestBody ActivityEnrollVO activityEnrollVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        ActivityEnroll activityEnroll = new ActivityEnroll();
        BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
        returnMsg = activityEnrollService.queryActivityEnroll(activityEnroll);
        return returnMsg;
    }
}
