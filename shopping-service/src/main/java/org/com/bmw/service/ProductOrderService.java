package org.com.bmw.service;

import org.com.bmw.model.Product;
import org.com.bmw.model.ProductOrder;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.CommonQueryBean;

public interface ProductOrderService {
    //下单
    ReturnMsg order(ProductOrder productOrder);

}
