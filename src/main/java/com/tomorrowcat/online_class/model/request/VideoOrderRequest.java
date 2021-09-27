package com.tomorrowcat.online_class.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
*@description: 下单dto
*@author: kim
*@create: 2021/3/28 15:51
*@version: 1.0.0
*/
public class VideoOrderRequest {


    @JsonProperty("video_id")
    private int videoId;

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
