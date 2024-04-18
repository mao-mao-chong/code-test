package org.com.bmw.model;

import org.com.bmw.util.CommonQueryBean;

import java.io.Serializable;

public class ReturnMsg implements Serializable {
    private String errorCode;

    private String errorMsg;

    private Object data;

    private CommonQueryBean commonQueryBean;

    public ReturnMsg(String errorCode,String errorMsg) {
        this.errorMsg=errorMsg;
        this.errorCode=errorCode;
    }
    public ReturnMsg(String errorCode,String errorMsg,Object data) {
        this.errorMsg=errorMsg;
        this.errorCode=errorCode;
        this.data=data;
    }
    public ReturnMsg() {

    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public CommonQueryBean getCommonQueryBean() {
        return commonQueryBean;
    }

    public void setCommonQueryBean(CommonQueryBean commonQueryBean) {
        this.commonQueryBean = commonQueryBean;
    }

    @Override
    public String toString() {
        return "ReturnMsg{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                ", commonQueryBean=" + commonQueryBean +
                '}';
    }
}
