package org.com.bmw.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleData extends BaseModel{
    //店铺id
    private Long storeId;
    //产品id
    private Long productId;
    //产品名称
    private String productName;
    //销售金额
    private BigDecimal saleAmount;
    //销售时间
    private Date saleDate;
    //非数据库维护字段
    private Date saleDateStart;
    private Date saleDateEnd;
    private String saleDateStartString;
    private String saleDateEndString;
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getSaleDateStart() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(saleDateStartString)){
                saleDateStart = sdf.parse(saleDateStartString);
            }
            return saleDateStart;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSaleDateEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(saleDateEndString)){
                this.saleDateEnd = sdf.parse(saleDateEndString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Date getSaleDateEnd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(saleDateEndString)){
                saleDateEnd = sdf.parse(saleDateEndString);
            }
            return saleDateStart;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSaleDateStart() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(StringUtils.isNotEmpty(saleDateStartString)){
                this.saleDateStart = sdf.parse(saleDateStartString);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "SaleData{" +
                "storeId=" + storeId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", saleAmount=" + saleAmount +
                ", saleDate=" + saleDate +
                ", saleDateStart=" + saleDateStart +
                ", saleDateEnd=" + saleDateEnd +
                ", saleDateStartString='" + saleDateStartString + '\'' +
                ", saleDateEndString='" + saleDateEndString + '\'' +
                '}';
    }
}
