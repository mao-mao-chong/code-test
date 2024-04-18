package org.com.bmw.util;

public class BusinessUtils {
    public static Integer calculateTotalPage(CommonQueryBean commonQueryBean){
        if(commonQueryBean!=null && commonQueryBean.getPageSize()!=null){
            if(commonQueryBean.getTotal()%commonQueryBean.getPageSize()!=0){
                return commonQueryBean.getTotal()/commonQueryBean.getPageSize()+1;
            }else{
                return commonQueryBean.getTotal()/commonQueryBean.getPageSize();
            }
        }
    return -1;
    }

}
