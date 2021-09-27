package com.tomorrowcat.online_class.exception;

import com.tomorrowcat.online_class.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @description: 异常处理类
 * @author: kim
 * @create: 2021-03-27 20:05
 * @version: 1.0.0
 */

@RestControllerAdvice   //默认返回json数据
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * @description:  发生异常就会执行此方法
     * @param: e
     * @return: JsonData
     */
    @ExceptionHandler(value = Exception.class)
    public JsonData handler(Exception e){
        logger.error("[ 系统异常 ]{}",e.getMessage());
        if(e instanceof KimException){
            KimException exception = (KimException) e;
            return JsonData.buildError(exception.getCode(), exception.getMsg());

        }else {
            return JsonData.buildError("全局异常，未知错误");
        }
    }

}