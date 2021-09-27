package com.tomorrowcat.online_class.service.impl;

import com.tomorrowcat.online_class.model.entity.User;
import com.tomorrowcat.online_class.mapper.UserMapper;
import com.tomorrowcat.online_class.service.UserService;
import com.tomorrowcat.online_class.utils.CommonUtils;
import com.tomorrowcat.online_class.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-27 21:05
 * @version: 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 保存用户
     * @param: userInfo
     * @return: int
     * @author: kim
     * @date: 2021/3/27 21:19
     */
    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);
        if (user != null){
            return userMapper.save(user);
        }

        return -1;
    }

    /**
     * @Description: 根据电话和密码查用户   用户登录   生成token
     * @param: phone
     * @param: pwd
     * @return: String
     */
    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));

        if (user == null){
            return null;
        }

        return JWTUtils.generateJsonWebToken(user);
    }

    /**
     * @Description: 通过id查询用户
     * @param: userId
     * @return: User
     */
    @Override
    public User findByUserId(Integer userId) {
        User user = userMapper.findById(userId);
        user.setPwd("");
        return user;
    }


    /**
     * 解析 user 对象
     * @param userInfo
     * @return
     */
    private User parseToUser(Map<String,String> userInfo){
        if(userInfo.containsKey("phone") && userInfo.containsKey("name") && userInfo.containsKey("pwd")){
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setPwd(CommonUtils.MD5(userInfo.get("pwd")));
            user.setPhone(userInfo.get("phone"));
            user.setCreateTime(new Date());
            user.setHeadImg(getRanImg());
            return user;
        }
        return null;
    }

    /**
     * 放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRanImg(){
        int len = headImg.length;
        Random random = new Random();
        int index = random.nextInt(len);
        return headImg[index];
    }
}