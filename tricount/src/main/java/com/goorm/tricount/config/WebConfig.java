package com.goorm.tricount.config;

import com.goorm.tricount.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/signup", "/login", "/h2-console/**");
    }

    @Bean
    public LoginCheckInterceptor loginCheckInterceptor() {return new LoginCheckInterceptor();}

} //class