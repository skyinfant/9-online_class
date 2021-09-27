package com.tomorrowcat.online_class.mapper;

import com.tomorrowcat.online_class.model.entity.PlayRecord;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-28 18:31
 * @version: 1.0.0
 */
public interface PlayRecordMapper {

    /**
     * @Description: 保存视频播放记录
     * @param: playRecord
     * @return: int
     */
    public int save(PlayRecord playRecord) ;
}