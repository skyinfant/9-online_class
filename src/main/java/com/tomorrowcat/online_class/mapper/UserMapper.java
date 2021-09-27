package com.tomorrowcat.online_class.mapper;

import com.tomorrowcat.online_class.model.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @description: mybatis映射接口
 * @author: Kim
 * @create: 2021-03-27 08:48
 */
public interface UserMapper {

    int save(User user);

    /**
     * @Description: 根据手机号查用户
     * @param: phone
     * @return: Video
     * @author: kim
     * @date: 2021/3/27 21:03
     */
    User findByPhone(@Param("phone") String phone);

    /**
     * @Description: 根据电话和密码查用户
     * @param: phone
     * @param: s
     * @return: User
     */
    User findByPhoneAndPwd(@Param("phone") String phone, @Param("pwd") String pwd);

    /**
     * @Description: 通过id查询用户
     * @param: userId
     * @return: User
     */
    User findById(@Param("user_id") Integer userId);
}