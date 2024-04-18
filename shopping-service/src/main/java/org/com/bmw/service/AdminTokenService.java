package org.com.bmw.service;

import org.com.bmw.model.AdminUser;
import org.com.bmw.util.Token;

public interface AdminTokenService {
    AdminUser getLoginUser(String token);
    Token saveToken(AdminUser loginUser);
    void deleteToken(String token);
    void refresh(AdminUser loginUser);

}