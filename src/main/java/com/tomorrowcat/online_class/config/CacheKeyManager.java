package com.tomorrowcat.online_class.config;

/**
 * @description: 缓存key管理类
 * @author: kim
 * @create: 2021-03-28 20:53
 * @version: 1.0.0
 */
public class CacheKeyManager {

    /**
     * 首页轮播图缓存key
     */
    public static final String INDEX_BANNER_KEY = "index:banner:list";
    /**
     * 首页视频列表缓存key
     */
    public static final String INDEX_VIDEOLIST_KEY = "index:video:list";

    /**
     * 视频详情缓存key, %s是视频id
     */
    public static final String VIDEO_DETAIL = "video:detail:%s";

}