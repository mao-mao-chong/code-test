package org.com.bmw.service;

import org.com.bmw.model.Product;
import org.com.bmw.model.ProductType;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.CommonQueryBean;

public interface ProductTypeService {
    /**
     * 商品类型查询
     * @param productType
     * @return
     */
    ReturnMsg queryProductType(ProductType productType);

    /**
     * 商品类型列表
     * @param productType
     * @param commonQueryBean
     * @return
     */
    ReturnMsg queryProductTypeList(ProductType productType, CommonQueryBean commonQueryBean);

    /**
     * 活动类型新增
     */
    ReturnMsg insertProductType(ProductType productType);
    /**
     * 商品类型修改
     */
    ReturnMsg modifyProductType(ProductType productType);
    /**
     * 商品类型删除
     */
    ReturnMsg delProductType(ProductType productType);
}
