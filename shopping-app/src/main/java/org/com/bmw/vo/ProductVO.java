package org.com.bmw.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVO extends BaseVO implements Serializable {
    //商品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Long productInventory;
    //图片地址‘
    private String pictureUrl;
    //商品介绍
    private String productIntroduce;
    //店铺id
    private Long storeId;
    //商品类型id
    private Long productTypeId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductInventory() {
        return productInventory;
    }

    public void setProductInventory(Long productInventory) {
        this.productInventory = productInventory;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getProductIntroduce() {
        return productIntroduce;
    }

    public void setProductIntroduce(String productIntroduce) {
        this.productIntroduce = productIntroduce;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productInventory=" + productInventory +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", productIntroduce='" + productIntroduce + '\'' +
                ", storeId=" + storeId +
                ", productTypeId=" + productTypeId +
                '}';
    }
}
