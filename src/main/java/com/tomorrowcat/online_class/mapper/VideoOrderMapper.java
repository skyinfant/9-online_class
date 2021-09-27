package com.tomorrowcat.online_class.mapper;

import com.tomorrowcat.online_class.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Kim
 * @create: 2021-03-28 16:04
 */
public interface VideoOrderMapper {

    /**
     * @Description: 根据用户id，视频id，状态查订单
     * @param: userId
     * @param: videoId
     * @return: VideoOrder
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") Integer userId,@Param("video_id") int videoId, @Param("state") int state);

    /**
     * @Description: 保存订单
     * @param: newVideoOrder
     * @return: int
     */
    int save(VideoOrder newVideoOrder);

    /**
     * @Description: 查询订单列表
     * @param: userId
     * @return: List<VideoOrder>
     */
    List<VideoOrder> findVideoOrderByUserId(@Param("user_id") int userId);
}