package com.sobev.nginxauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NginxAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NginxAuthServerApplication.class, args);
    }

}
