package com.tomorrowcat.online_class.service;

import com.tomorrowcat.online_class.model.entity.User;

import java.util.Map;

/**
 * @description:
 * @author: Kim
 * @create: 2021-03-27 20:43
 */
public interface UserService {

    /**
     * @Description: 新增用户
     * @param: userInfo
     * @return: int
     * @author: kim
     * @date: 2021/3/27 21:05
     */
    int save(Map<String,String> userInfo);

    /**
     * @Description: 根据电话和密码查询用户
     * @param: phone
     * @param: pwd
     * @return: String
     */
    String findByPhoneAndPwd(String phone, String pwd);

    /**
     * @Description: 通过id查询用户
     * @param: userId
     * @return: User
     */
    User findByUserId(Integer userId);
}