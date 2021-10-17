package com.sobev.nginxauthserver.controller;

import com.sobev.nginxauthserver.common.model.ResponseData;
import com.sobev.nginxauthserver.common.vo.NginxAuthVo;
import com.sobev.nginxauthserver.utils.http.CookieUtils;
import com.sobev.nginxauthserver.utils.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping(value = "/stream/play_on")
    @ResponseBody
    public ResponseData<String> authenticate(NginxAuthVo nginxAuthVo, HttpServletResponse response){
        String userId = jwtUtils.getUserIdFromJToken(nginxAuthVo.getToken());
        boolean validate = jwtUtils.validateToken(nginxAuthVo.getToken());
        if(!validate){
            //set 203 no NON_AUTHORITATIVE_INFORMATION
            response.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            return ResponseData.error("Validation Error");
        }
        CookieUtils.addCookie(response,"good","yes",3600);
        response.setStatus(HttpStatus.OK.value());
        return ResponseData.success("ok");
    }
}
