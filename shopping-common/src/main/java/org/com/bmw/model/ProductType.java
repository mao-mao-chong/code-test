package org.com.bmw.model;

import java.io.Serializable;

public class ProductType  extends BaseModel{
    //商品类型名称
    private String productTypeName;

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "productTypeName='" + productTypeName + '\'' +
                '}';
    }
}
