package com.tomorrowcat.online_class.service;

import com.tomorrowcat.online_class.model.entity.Video;
import com.tomorrowcat.online_class.model.entity.VideoBanner;

import java.util.List;

/**
 * @description:
 * @author: Kim
 * @create: 2021-03-27 08:55
 */
public interface VideoService {
    /**
     * @Description: 查询视频列表
     * @param:
     * @return: List<Video>
     * @author: kim
     * @date: 2021/3/27 8:52
     */
    List<Video> listVideo();

    /**
     * 轮播图列表
     * @return
     */
    List<VideoBanner> listBanner();

    /**
     * @Description: 视频详情
     * @param: videoId
     * @return: Video
     * @author: kim
     * @date: 2021/3/27 17:21
     */
    Video findDetailById(int videoId);
}