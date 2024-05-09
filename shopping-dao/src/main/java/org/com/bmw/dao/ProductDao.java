package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.MapKey;
import org.com.bmw.model.Activity;
import org.com.bmw.model.Product;
import org.com.bmw.util.CommonQueryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductDao {
    /**
     * 商品查询
     * @param product
     * @return
     */
    Product queryProduct(Product product);

    /**
     * 商品列表
     * @param product
     * @param commonQueryBean
     * @return
     */
    List<Product> queryProductList(@Param("product") Product product, @Param("commonQueryBean") CommonQueryBean commonQueryBean);

    /**
     * 商品新增
     */
    int insertProduct(Product product);
    /**
     * 商品删除
     */
    int modifyProduct(Product product);

    int count(Product product);

    List<Product> queryProductByProductIdList(List<Long> list);

}
