package com.tomorrowcat.online_class.interceptor;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 处理跨域   前端与后端不同源（协议，域名，端口）时，后端返回的数据前端将无法使用，所以要在后端返回数据时声明允许其使用
 *                在InterceptorConfig.java中配置拦截规则
 * @author: kim
 * @create: 2021-03-29 22:43
 * @version: 1.0.0
 */
@Component
public class CorsInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //表示接受任意域名的请求,也可以指定域名
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));

        //该字段可选，是个布尔值，表示是否可以携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");

        response.setHeader("Access-Control-Allow-Headers", "*");


        //这里可以不加，但是其他语言开发的话记得处理options请求
        /**
         * 非简单请求是那种对服务器有特殊要求的请求，
         * 比如请求方式是PUT或者DELETE，或者Content-Type字段类型是application/json。
         * 都会在正式通信之前，增加一次HTTP请求，称之为预检。浏览器会先询问服务器，当前网页所在域名是否在服务器的许可名单之中，
         * 服务器允许之后，浏览器会发出正式的XMLHttpRequest请求
         */
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            //非简单请求在正式请求之前要先发一个options请求预检
            //这里是让预检通过，让浏览器发出正式请求
            return true;
        }

        return true;
    }




    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}