package com.sobev.nginxauthserver.config;

import com.sobev.nginxauthserver.utils.jwt.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;

    @Bean
    public CustomInterceptor customInterceptor(){
        return new CustomInterceptor();
    }

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(customInterceptor());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
                .allowedOriginPatterns("*")//使用allowedOriginPatterns不要用allowedOrigin否则SpringBoot报错
                .maxAge(MAX_AGE_SECS);
    }
}
