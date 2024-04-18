package org.com.bmw.security;


import lombok.Getter;
import org.com.bmw.model.ReturnMsg;
import org.com.bmw.util.Constant;
import org.springframework.security.core.AuthenticationException;


@Getter
public class BaseAuthenticationException extends AuthenticationException {
    private String code;

    public BaseAuthenticationException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public static BaseAuthenticationException error(Constant constant) {
        return new BaseAuthenticationException(constant.getCode(), constant.getMessage());
    }
    public ReturnMsg resp() {
        return new ReturnMsg(code, getMessage());
    }
}
