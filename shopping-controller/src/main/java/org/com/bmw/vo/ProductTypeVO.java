package org.com.bmw.vo;

import org.com.bmw.model.BaseModel;

import java.io.Serializable;

public class ProductTypeVO extends BaseVO implements Serializable {
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
