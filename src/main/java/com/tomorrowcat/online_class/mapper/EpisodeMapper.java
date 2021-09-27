package com.tomorrowcat.online_class.mapper;

import com.tomorrowcat.online_class.model.entity.Episode;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 集
 * @author: Kim
 * @create: 2021-03-28 18:28
 */
public interface EpisodeMapper {

    /**
     * @Description: 查询第一集
     * @param: videoId
     * @return: Episode
     */
    Episode findFirstEpisodeByVideoId(@Param("video_id") int videoId);
}