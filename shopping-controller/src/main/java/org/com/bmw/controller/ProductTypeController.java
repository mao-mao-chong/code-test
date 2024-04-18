package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.Product;
import org.com.bmw.model.ProductType;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductTypeService;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.com.bmw.vo.ProductTypeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/productType")
@Slf4j
public class ProductTypeController {
    @Autowired
    ProductTypeService productTypeService;
    //商品类型列表查询
    @RequestMapping(value = "/queryProductTypeList", method = RequestMethod.POST)
    public ReturnMsg queryProductTypeList(@RequestBody ProductTypeVO productTypeVO){
        log.info("商品类型列表查询：{}"+productTypeVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            ProductType productType = new ProductType();
            CommonQueryBean commonQueryBean = new CommonQueryBean();
            BeanUtils.copyProperties(productTypeVO,productType);
            BeanUtils.copyProperties(productTypeVO,commonQueryBean);
            returnMsg = productTypeService.queryProductTypeList(productType,commonQueryBean);
        }catch(Exception e){
            log.info("==={}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    //商品类型新增
    @RequestMapping(value = "/addProductType", method = RequestMethod.POST)
    public ReturnMsg addProductType(@RequestBody ProductTypeVO productTypeVO){
        log.info("商品类型新增:{}",productTypeVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            ProductType productType = new ProductType();
            BeanUtils.copyProperties(productTypeVO,productType);
            returnMsg = productTypeService.insertProductType(productType);
        }catch(Exception e){
            log.info("==={}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }

        return returnMsg;
    }
    //商品类型修改
    @RequestMapping(value = "/modifyProductType", method = RequestMethod.POST)
    public ReturnMsg modifyProductType(@RequestBody ProductTypeVO productTypeVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            log.info("商品类型修改：{}",productTypeVO);
            ProductType productType = new ProductType();
            BeanUtils.copyProperties(productTypeVO,productType);
            returnMsg = productTypeService.modifyProductType(productType);
        }catch(Exception e){
            log.info("商品类型修改发生异常:{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    // 商品类型删除
    @RequestMapping(value = "/delProductType", method = RequestMethod.POST)
    public ReturnMsg delProductType(@RequestBody ProductTypeVO productTypeVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            ProductType productType = new ProductType();
            BeanUtils.copyProperties(productTypeVO,productType);
            returnMsg = productTypeService.delProductType(productType);
        }catch(Exception e){
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    //商品类型查询
    @RequestMapping(value = "/queryProductType", method = RequestMethod.POST)
    public ReturnMsg queryProductType(@RequestBody ProductTypeVO productTypeVO){
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            ProductType productType = new ProductType();
            BeanUtils.copyProperties(productTypeVO,productType);
            returnMsg = productTypeService.queryProductType(productType);
        }catch(Exception e){
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
}
