package com.tomorrowcat.online_class.config;

import com.tomorrowcat.online_class.interceptor.CorsInterceptor;
import com.tomorrowcat.online_class.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 拦截器配置
 * @author: kim
 * @create: 2021-03-28 10:36
 * @version: 1.0.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 拦截全部路径，这个跨域处理需要放在最上面
         */
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");



        //拦截全部pri的路径但排除 登陆和注册
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径   斜杠一定要加
                .excludePathPatterns("/api/v1/pri/user/register","/api/v1/pri/user/login");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}













