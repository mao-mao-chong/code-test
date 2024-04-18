package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.Activity;
import org.com.bmw.model.Product;
import org.com.bmw.model.ProductType;
import org.com.bmw.util.CommonQueryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeDao {
    /**
     * 商品类型查询
     * @param productType
     * @return
     */
    ProductType queryProductType(ProductType productType);

    /**
     * 商品列表
     * @param productType
     * @param commonQueryBean
     * @return
     */
    List<ProductType> queryProductTypeList(@Param("productType") ProductType productType, @Param("commonQueryBean") CommonQueryBean commonQueryBean);

    /**
     * 商品新增
     */
    int insertProductType(ProductType productType);
    /**
     * 商品删除
     */
    int modifyProductType(ProductType productType);

    int count(ProductType productType);
}
