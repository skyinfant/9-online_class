package com.tomorrowcat.online_class.controller;

import com.tomorrowcat.online_class.model.entity.User;
import com.tomorrowcat.online_class.model.request.LoginRequest;
import com.tomorrowcat.online_class.service.UserService;
import com.tomorrowcat.online_class.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-27 20:41
 * @version: 1.0.0
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 注册
     * @param: userInfo
     * @return: JsonData
     * @author: kim
     * @date: 2021/3/27 21:24
     */
    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo){
        int rows = userService.save(userInfo);

        return rows == 1 ? JsonData.buildSuccess() : JsonData.buildError("注册失败，请重试");
    }

    /**
     * @Description: 用户登录
     * @param: loginRequest DTO
     * @return: JsonData
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){
        //根据电话和密码查询是否有此用户，有的话生成token
        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(),loginRequest.getPwd());

        return token != null ? JsonData.buildSuccess(token) : JsonData.buildError("登录失败，账号密码错误");
    }

    /**
     * @Description: 根据id查询用户
     * 用户携带token访问此方法--被拦截器拦截，如果token合法--把用户信息存入request---在此方法可以从request中取出user_id
     * @param: request
     * @return: JsonData
     */
    @GetMapping("find_by_token")
    public JsonData findByToken(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        if(userId == null){
            return JsonData.buildError("查询用户失败");
        }

        User user = userService.findByUserId(userId);

        return JsonData.buildSuccess(user);

    }




}