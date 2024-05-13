package org.com.bmw.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.com.bmw.model.AdminUser;
import org.com.bmw.model.Role;
import org.com.bmw.model.User;
import org.com.bmw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * 实际的后台admin用户名和密码验证方法.
 */
@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService adminService;

    /**
     * 其实这样就完成了认证的过程，能获取到数据库中配置的用户信息     *
     * @param username
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取该用户的信息
        User user = new User();
        user.setUserName(username);
        User currUser = adminService.selectByUser(user);
        if (currUser == null) {//用户不存在报错
            throw new UsernameNotFoundException("用户不存在");
        }
        AdminUser adminUser = new AdminUser();
        try {
            BeanUtils.copyProperties(adminUser,currUser );
            adminUser.setUsername(currUser.getUserName());//userName大小写转换
            //赋值角色
            dealAdminRoles(adminUser);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        adminUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(adminUser.getRoles()));
        return adminUser;
    }

    /**
     * 获取用户角色
     */
    private void dealAdminRoles(AdminUser adminUser) {
        List<Role> roleList = adminService.selectUserRole(adminUser.getId());
        String roles=null;
        StringBuffer rolesBuffer = new StringBuffer();
        if(!CollectionUtils.isEmpty(roleList)){
            for(Role role : roleList){
                rolesBuffer.append(role.getRoleName()+",");
            }
            roles = rolesBuffer.substring(0,rolesBuffer.length()-1);
        }
        adminUser.setRoles(roles);
    }

}
