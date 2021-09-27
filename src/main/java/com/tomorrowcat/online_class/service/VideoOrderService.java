package com.tomorrowcat.online_class.service;

import com.tomorrowcat.online_class.model.entity.VideoOrder;

import java.util.List;

/**
 * @description: 下单接口
 * @author: Kim
 * @create: 2021-03-28 15:54
 */
public interface VideoOrderService {

    /**
     * @Description: 保存订单
     * @param: userId
     * @param: videoId
     * @return: int
     */
    int save(Integer userId, int videoId);

    /**
     * @Description: 查询订单列表
     * @param: 
     * @return: List<VideoOrder>
     */
    List<VideoOrder> findVideoOrderByUserId(int userId);
}