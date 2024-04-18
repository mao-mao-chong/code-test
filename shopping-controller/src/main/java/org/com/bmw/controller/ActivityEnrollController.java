package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ActivityEnrollController {

    @Autowired
    ActivityEnrollService activityEnrollService;
    // 活动报名
    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public ReturnMsg enroll(@RequestBody ActivityEnrollVO activityEnrollVO){
        log.info("活动柜报名：{}",activityEnrollVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try{
            ActivityEnroll activityEnroll = new ActivityEnroll();
            BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
            returnMsg = activityEnrollService.enroll(activityEnroll);
        }catch(Exception e){
            log.info("活动报名发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }

        return returnMsg;
    }
    // 取消报名
    @RequestMapping(value = "/cancelEnroll", method = RequestMethod.POST)
    public ReturnMsg cancelEnroll(@RequestBody ActivityEnrollVO activityEnrollVO){
        log.info("取消活动报名：{}",activityEnrollVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            ActivityEnroll activityEnroll = new ActivityEnroll();
            BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
            returnMsg = activityEnrollService.cancelEnroll(activityEnroll);
        }catch(Exception e){
            log.info("取消活动报名发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    // 报名活动列表查询
    @RequestMapping(value = "/queryActivityEnrollList", method = RequestMethod.POST)
    public ReturnMsg activityEnrollList(@RequestBody ActivityEnrollVO activityEnrollVO){
        log.info("报名活动列表查询:{}",activityEnrollVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try{
            ActivityEnroll activityEnroll = new ActivityEnroll();
            CommonQueryBean commonQueryBean = new CommonQueryBean();
            BeanUtils.copyProperties(activityEnrollVO,commonQueryBean);
            BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
            returnMsg = activityEnrollService.queryActivityEnrollList(activityEnroll,commonQueryBean);
        }catch(Exception e){
            log.info("报名活动列表查询发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }

        return returnMsg;
    }
    // 报名活动查询
    @RequestMapping(value = "/queryActivityEnroll", method = RequestMethod.POST)
    public ReturnMsg queryActivityEnroll(@RequestBody ActivityEnrollVO activityEnrollVO){
        log.info("报名活动查询:{}",activityEnrollVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try{
            ActivityEnroll activityEnroll = new ActivityEnroll();
            BeanUtils.copyProperties(activityEnrollVO,activityEnroll);
            returnMsg = activityEnrollService.queryActivityEnroll(activityEnroll);
        }catch(Exception e){
            log.info("报名活动查询发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }

        return returnMsg;
    }
}
