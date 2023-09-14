package com.sobev.nginxauthserver.config;

import com.sobev.nginxauthserver.common.JwtProperties;
import com.sobev.nginxauthserver.common.exception.AuthException;
import com.sobev.nginxauthserver.utils.http.CookieUtils;
import com.sobev.nginxauthserver.utils.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Optional;

public class CustomInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        Optional<Cookie> authorization = CookieUtils.getCookie(request, "Authorization");
        if (!authorization.isPresent()) {
            writer = response.getWriter();
            writer.println("Please Login!");
            response.sendError(500, "Please Login!");
            return false;
        }
        jwtUtils.setJwtProperties(jwtProperties);
        logger.info(authorization.get().getValue());
        String value = authorization.get().getValue();
        boolean validate = jwtUtils.validateToken(value);
        if (!validate) {
            response.sendError(500, "Error LoginControllerExceptionAdvice!");
            writer = response.getWriter();
            writer.println("Please Login!");
            throw new AuthException("Auth validate error");
        }

        return true;
    }
}
