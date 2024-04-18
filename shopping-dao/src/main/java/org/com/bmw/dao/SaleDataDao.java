package org.com.bmw.dao;

import io.lettuce.core.dynamic.annotation.Param;
import org.com.bmw.model.Product;
import org.com.bmw.model.SaleData;
import org.com.bmw.util.CommonQueryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDataDao {

    List<SaleData> querySaleDataList(@Param("saleData")SaleData saleData,@Param("commonQueryBean") CommonQueryBean commonQueryBean);

    int count(SaleData saleData);

    int insertSaleData(SaleData saleData);
}
