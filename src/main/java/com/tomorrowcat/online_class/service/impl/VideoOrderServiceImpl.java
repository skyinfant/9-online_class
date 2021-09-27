package com.tomorrowcat.online_class.service.impl;


import com.tomorrowcat.online_class.exception.KimException;
import com.tomorrowcat.online_class.mapper.EpisodeMapper;
import com.tomorrowcat.online_class.mapper.PlayRecordMapper;
import com.tomorrowcat.online_class.mapper.VideoMapper;
import com.tomorrowcat.online_class.mapper.VideoOrderMapper;
import com.tomorrowcat.online_class.model.entity.Episode;
import com.tomorrowcat.online_class.model.entity.PlayRecord;
import com.tomorrowcat.online_class.model.entity.Video;
import com.tomorrowcat.online_class.model.entity.VideoOrder;
import com.tomorrowcat.online_class.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-28 15:54
 * @version: 1.0.0
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;


    /**
     * 下单操作
     * 未来版本：优惠券抵扣，风控用户检查，生成订单基础信息，生成支付信息
     *
     * @param userId
     * @param videoId
     * @return
     */
    @Transactional   //开启事务
    @Override
    public int save(Integer userId, int videoId) {
        //判断是否已经购买过，已购买过的话不再购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);
        if (videoOrder != null) {
            return 0;
        }

        //查询第一集
        Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
        if (episode == null) {
            throw new KimException(-1, "视频没有集信息，请运营人员检查");
        }


        Video video = videoMapper.findById(videoId);

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());
        //保存订单
        int rows = videoOrderMapper.save(newVideoOrder);


        //生成播放记录
        if (rows == 1) {
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);

            playRecordMapper.save(playRecord);
        }

        return rows;


    }

    /**
     * @Description: 查询订单列表
     * @param:
     * @return: List<VideoOrder>
     */
    @Override
    public List<VideoOrder> findVideoOrderByUserId(int userId) {
        return videoOrderMapper.findVideoOrderByUserId(userId);
    }
}