package com.sobev.nginxauthserver.controller;

import com.sobev.nginxauthserver.common.model.ResponseData;
import com.sobev.nginxauthserver.utils.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping(value = "/stream/auth")
    @ResponseBody
    public ResponseData<String> play(@RequestParam("auth_key") String auth_key, HttpServletResponse response) {
        auth_key = auth_key.substring(1);
        logger.info(auth_key);
        boolean validate = jwtUtils.validateToken(auth_key);
        //expire error or jwt format exception
        if (!validate) {
            //set 203 no NON_AUTHORITATIVE_INFORMATION
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseData.error("Validation Error");
        }
        String userId = jwtUtils.getUserIdFromJToken(auth_key);
        // check the db the user has permission or what  like access to the room
        response.setStatus(HttpStatus.OK.value());
        return ResponseData.success("ok");
    }
}
