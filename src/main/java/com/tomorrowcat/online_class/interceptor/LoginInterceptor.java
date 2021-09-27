package com.tomorrowcat.online_class.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomorrowcat.online_class.utils.JWTUtils;
import com.tomorrowcat.online_class.utils.JsonData;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @description: 登陆拦截器,验证用户是否登陆    在InterceptorConfig.java中配置拦截规则
 * @author: kim
 * @create: 2021-03-28 10:09
 * @version: 1.0.0
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String accessToken = request.getHeader("token");
            if (accessToken == null){
                accessToken = request.getParameter("token");
            }

            if (StringUtils.isNotBlank(accessToken)){
                Claims claims = JWTUtils.checkJWT(accessToken);


                //vue前端已经设置了如果没有token则跳转到登陆页面
                //所以如果claims为null，则一定是token过期或者token是伪造的
                if(claims == null){
                    sendJsonMessage(response, JsonData.buildError("登陆过期，请重新登陆"));
                    return false;
                }

                //token有效，则把用户信息存进request
                Integer id = (Integer) claims.get("id");
                String name = (String) claims.get("name");

                request.setAttribute("user_id", id);
                request.setAttribute("name", name);
                //用户已经登录
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        sendJsonMessage(response, JsonData.buildError("登录过期，重新登录"));

        return false;
    }



    /**
     * 响应json数据给前端
     * 为什么要把jsondata对象转为json再返回前端？  因为在controller中springboot自动会把对象转为json格式返回，
     * 而这里是返回异常数据，不会到达controller，所以要手动转为json返回，
     * 否则返回的是com.tomorrowcat.online_class.utils.JsonData@5d34393f这种形式
     * @param res
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse res,Object obj){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            res.setContentType("application/json;charset=utf-8");
            PrintWriter writer = res.getWriter();
            writer.println(objectMapper.writeValueAsString(obj));
            writer.close();
            res.flushBuffer();

        }catch (Exception e){
            e.printStackTrace();
        }
    }






    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}