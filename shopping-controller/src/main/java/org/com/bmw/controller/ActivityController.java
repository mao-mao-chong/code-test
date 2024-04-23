package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.Activity;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ActivityService;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.com.bmw.util.RedisUtil;
import org.com.bmw.vo.ActivityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/activity")
@Slf4j
public class ActivityController {
    @Autowired
    ActivityService activityService;

    //活动新增
    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ReturnMsg addActivity(@RequestBody ActivityVO activityVO){
        log.info("活动新增：{}",activityVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Activity activity = new Activity();
            BeanUtils.copyProperties(activityVO,activity);
            returnMsg = activityService.insertActivity(activity);
        }catch(Exception e){
            log.info("活动新增发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }

        return returnMsg;
    }
    // 活动修改
    @RequestMapping(value = "/modifyActivity", method = RequestMethod.POST)
    public ReturnMsg modifyActivity(@RequestBody ActivityVO activityVO){
        log.info("活动修改：{}",activityVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            log.info("活动信息修改：{}",activityVO);
            Activity activity = new Activity();
            BeanUtils.copyProperties(activityVO,activity);
            returnMsg = activityService.modifyActivity(activity);
        }catch(Exception e){
            log.info("活动信息修改发生异常:{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    // 活动删除
    @RequestMapping(value = "/delActivity", method = RequestMethod.POST)
    public ReturnMsg delActivity(@RequestBody ActivityVO activityVO){
        log.info("活动删除：{}",activityVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Activity activity = new Activity();
            BeanUtils.copyProperties(activityVO,activity);
            returnMsg = activityService.delActivity(activity);
        }catch(Exception e){
            log.info("活动删除发生异常：{}",activityVO);
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    // 单个活动查询
    @RequestMapping(value = "/queryActivity", method = RequestMethod.POST)
    public ReturnMsg queryActivity(@RequestBody ActivityVO activityVO){
        log.info("单个活动查询：{}",activityVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Activity activity = new Activity();
            BeanUtils.copyProperties(activityVO,activity);
            returnMsg = activityService.queryActivity(activity);
        }catch(Exception e){
            log.info("单个活动查询发生异常：{}",activityVO);
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    // 活动列表查询
    @RequestMapping(value = "/queryActivityList", method = RequestMethod.POST)
    public ReturnMsg queryActivityList( @RequestBody ActivityVO activityVO){
        log.info("活动列表查询：{}",activityVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Activity activity = new Activity();
            CommonQueryBean commonQueryBean = new CommonQueryBean();
            BeanUtils.copyProperties(activityVO,activity);
            BeanUtils.copyProperties(activityVO,commonQueryBean);
            returnMsg = activityService.queryActivityList(activity,commonQueryBean);
        }catch(Exception e){
            log.info("活动列表查询发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
}

