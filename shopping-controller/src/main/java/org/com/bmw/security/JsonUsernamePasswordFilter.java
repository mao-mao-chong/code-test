package org.com.bmw.security;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.com.bmw.util.Constant;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("----------第三步---------------");

        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        //attempt Authentication when Content-Type is json
        if (request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            Map<String,Object> map = readParamsFromRequest(request);

            String userName =(String)map.get("userName");
            String password =(String)map.get("password");
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);

        } else {
            throw BaseAuthenticationException.error(Constant.FAILED);
        }

    }
    /**
     * 从请求中读取参数，转为 Map
     */
    private static Map<String, Object> readParamsFromRequest(HttpServletRequest request) {
        Map<String, Object> map = null;

        try {
            // 从 request 中读取 Body 数据
            ServletInputStream inputStream = request.getInputStream();
            StringBuilder content = new StringBuilder();
            byte[] b = new byte[1024];
            int lens;
            while ((lens = inputStream.read(b)) > 0) {
                content.append(new String(b, 0, lens));
            }

            // 将字符串转换为 Map 集合
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(content.toString(), Map.class);
        } catch (Exception ex) {
            log.error("Error occurred while read params from request");
        }

        return map;
    }
}
