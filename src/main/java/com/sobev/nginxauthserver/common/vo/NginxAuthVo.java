package com.sobev.nginxauthserver.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class NginxAuthVo implements Serializable {
    private static final long serialVersionUID = 5829359004285681297L;

    private String username;

    private String token;
}
