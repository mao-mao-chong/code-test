package org.com.bmw.dao;

import org.com.bmw.model.ProductOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderDao {

    List<ProductOrder> queryProductOrderAmount();
}
