package org.com.bmw.service;

import org.com.bmw.model.Activity;
import org.com.bmw.model.Product;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.CommonQueryBean;

public interface ProductService {
    /**
     * 商品查询
     * @param product
     * @return
     */
    ReturnMsg queryProduct(Product product);

    /**
     * 商品列表
     * @param product
     * @param commonQueryBean
     * @return
     */
    ReturnMsg queryProductList(Product product, CommonQueryBean commonQueryBean);

    /**
     * 活动新增
     */
    ReturnMsg insertProduct(Product product);
    /**
     * 商品修改
     */
    ReturnMsg modifyProduct(Product product);
    /**
     * 商品删除
     */
    ReturnMsg delProduct(Product product);
}
