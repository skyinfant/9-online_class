package com.tomorrowcat.online_class.controller;

import com.tomorrowcat.online_class.model.entity.Video;
import com.tomorrowcat.online_class.model.entity.VideoBanner;
import com.tomorrowcat.online_class.service.VideoService;
import com.tomorrowcat.online_class.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-27 09:05
 * @version: 1.0.0
 */
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    private final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    /**
     * @Description: 视频列表
     * @param: 
     * @return: Object
     * @author: kim
     * @date: 2021/3/27 16:37
     */
    @GetMapping("list")
    public Object listVideo(){
        List<Video> videos = videoService.listVideo();
        logger.info("get video list.... videoSize= {} ",videos.size());
        return JsonData.buildSuccess(videos);
    }

    /**
     * 轮播图列表
     * @return
     */
    @GetMapping("list_banner")
    public JsonData indexBanner(){
        List<VideoBanner> bannerList = videoService.listBanner();

        //模拟异常
        //int i = 1/0;

        return JsonData.buildSuccess(bannerList);
    }


    /**
     * 查询视频详情，包含章，集信息，三表关联
     * @param videoId
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true) int videoId){

        Video video = videoService.findDetailById(videoId);

        return JsonData.buildSuccess(video);

    }
}