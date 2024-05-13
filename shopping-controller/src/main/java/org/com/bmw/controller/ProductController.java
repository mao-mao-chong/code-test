package org.com.bmw.controller;

import lombok.extern.slf4j.Slf4j;
import org.com.bmw.model.Activity;
import org.com.bmw.model.Product;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.service.ProductService;
import org.com.bmw.util.CommonQueryBean;
import org.com.bmw.util.Constant;
import org.com.bmw.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
@Slf4j
@PreAuthorize("hasAuthority('business')")
public class ProductController {
    @Autowired
    ProductService productService;

    //商品列表查询
    @RequestMapping(value = "/queryProductList", method = RequestMethod.POST)
    public ReturnMsg queryProductList(@RequestBody ProductVO productVO){
        log.info("商品列表查询：{}"+productVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Product product = new Product();
            CommonQueryBean commonQueryBean = new CommonQueryBean();
            BeanUtils.copyProperties(productVO,product);
            BeanUtils.copyProperties(productVO,commonQueryBean);
            returnMsg = productService.queryProductList(product,commonQueryBean);
        }catch(Exception e){
            log.info("商品列表查询发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }

    //商品查询
    @RequestMapping(value = "/queryProduct", method = RequestMethod.POST)
    public ReturnMsg queryProduct(@RequestBody ProductVO productVO){
        log.info("商品查询：{}"+productVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productVO,product);
            returnMsg = productService.queryProduct(product);
        }catch(Exception e){
            log.info("商品查询发生异常：{}"+productVO);
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    //商品新增
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ReturnMsg addProduct(@RequestBody ProductVO productVO){
        log.info("商品新增:{}",productVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productVO,product);
            returnMsg = productService.insertProduct(product);
        }catch(Exception e){
            log.info("商品新增发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }

        return returnMsg;
    }
    //商品修改
    @RequestMapping(value = "/modifyProduct", method = RequestMethod.POST)
    public ReturnMsg modifyProduct(@RequestBody ProductVO productVO){
        log.info("商品修改：{}",productVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {

            Product product = new Product();
            BeanUtils.copyProperties(productVO,product);
            returnMsg = productService.modifyProduct(product);
        }catch(Exception e){
            log.info("商品修改发生异常:{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
    // 商品删除
    @RequestMapping(value = "/delProduct", method = RequestMethod.POST)
    public ReturnMsg delProduct(@RequestBody ProductVO productVO){
        log.info("商品删除：{}",productVO);
        ReturnMsg returnMsg = new ReturnMsg(Constant.SUCCESS.getCode(),Constant.SUCCESS.getMessage());
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productVO,product);
            returnMsg = productService.delProduct(product);
        }catch(Exception e){
            log.info("商品删除发生异常：{}",e.getMessage());
            returnMsg = new ReturnMsg(Constant.SYSTEM_ERROR.getCode(),Constant.SYSTEM_ERROR.getMessage());
        }
        return returnMsg;
    }
}
