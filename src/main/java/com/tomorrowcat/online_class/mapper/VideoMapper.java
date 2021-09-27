package com.tomorrowcat.online_class.mapper;

import com.tomorrowcat.online_class.model.entity.Video;
import com.tomorrowcat.online_class.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: mybatis映射接口
 * @author: Kim
 * @create: 2021-03-27 08:48
 */
public interface VideoMapper {

    /**
     * @Description: 查询视频列表
     * @param: 
     * @return: List<Video>
     * @author: kim
     * @date: 2021/3/27 8:52
     */
    List<Video>  listVideo();

    /**
     * @Description: 视频轮播图
     * @param: 
     * @return: List<VideoBanner>
     * @author: kim
     * @date: 2021/3/27 17:07
     */
    List<VideoBanner> listVideoBanner();


    /**
     * @Description: 查询视频详情
     * @param: videoId
     * @return: Video
     * @author: kim
     * @date: 2021/3/27 18:17
     */
    Video findDetailById(@Param("video_id") int videoId);

    /**
     * @Description: 根据id查找
     * @param: userId
     * @return: Video
     */
    Video findById(@Param("video_id") Integer videoId);
}