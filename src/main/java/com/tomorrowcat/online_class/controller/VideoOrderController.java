package com.tomorrowcat.online_class.controller;

import com.tomorrowcat.online_class.model.entity.VideoOrder;
import com.tomorrowcat.online_class.model.request.VideoOrderRequest;
import com.tomorrowcat.online_class.service.VideoOrderService;
import com.tomorrowcat.online_class.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: kim
 * @create: 2021-03-28 15:52
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {
    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * @Description: 下单          注意：如果该用户已买过的视频下单会失败，不能重复买
     * @param: videoOrderRequest  dto
     * @param: request
     * @return: JsonData
     */
    @PostMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        //若用户已登录，则在登陆拦截器中已经把user_id存入req，若用户未登陆，是到达不了这个方法的
        Integer userId = (Integer) request.getAttribute("user_id");

        int rows = videoOrderService.save(userId,videoOrderRequest.getVideoId());

        return rows == 0 ? JsonData.buildError("下单失败") : JsonData.buildSuccess();

    }

    /**
     * @Description: 查询某个用户的订单列表
     * @param: request
     * @return: JsonData
     */
    @GetMapping("list")
    public JsonData list(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrders = videoOrderService.findVideoOrderByUserId(userId);

        return JsonData.buildSuccess(videoOrders);
    }

}




















