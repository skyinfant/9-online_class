package com.tomorrowcat.online_class.service.impl;

import com.tomorrowcat.online_class.config.CacheKeyManager;
import com.tomorrowcat.online_class.model.entity.Video;
import com.tomorrowcat.online_class.model.entity.VideoBanner;
import com.tomorrowcat.online_class.mapper.VideoMapper;
import com.tomorrowcat.online_class.service.VideoService;
import com.tomorrowcat.online_class.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-27 08:56
 * @version: 1.0.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private BaseCache baseCache;

    /**
     * @Description: 视频列表
     * @param:
     * @return: List<Video>
     */
    @Override
    public List<Video> listVideo() {
        try {
            //先从缓存里面取，取不到再去查库   相当于hashmap，key-value
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEOLIST_KEY, () -> {

                List<Video> videoList = videoMapper.listVideo();
                System.out.println("从数据库里面找视频列表");
                //这里是返回给外面的cacheObj，而不是返回给controller
                return videoList;
            });

            //从缓存里取，或从数据库里取，都会到这里
            if (cacheObj instanceof List) {
                List<Video> videoList = (List<Video>) cacheObj;

                return videoList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //若发生异常，可以返回兜底数据
        return null;

    }


    /**
     * 轮播图列表
     *
     * @return
     */
    @Override
    public List<VideoBanner> listBanner() {
        try {
            //先从缓存里面取，取不到再去查库   相当于hashmap，key-value   缓存有效时间为10分钟
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {

                List<VideoBanner> bannerList = videoMapper.listVideoBanner();
                System.out.println("从数据库里面找轮播图列表");
                //这里是返回给外面的cacheObj，而不是返回给controller
                return bannerList;
            });

            //从缓存里取，或从数据库里取，都会到这里
            if (cacheObj instanceof List) {
                List<VideoBanner> bannerList = (List<VideoBanner>) cacheObj;

                return bannerList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * @Description: 查询视频详情
     * @param: videoId
     * @return: Video
     * @author: kim
     * @date: 2021/3/27 18:37
     */
    @Override
    public Video findDetailById(int videoId) {

        //单独构建一个缓存key，每个视频的key是不一样的
        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL, videoId);

        try {                           //缓存有效时间为1小时
            Object cacheObj = baseCache.getOneHourCache().get(videoCacheKey, () -> {
                Video video = videoMapper.findDetailById(videoId);

                return video;
            });

            if(cacheObj instanceof Video){
                Video video = (Video) cacheObj;
                return video;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;


    }
}

