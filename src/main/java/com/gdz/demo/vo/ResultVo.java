package com.gdz.demo.vo;


import lombok.Data;


@Data
public class ResultVo<T> {

    /**
     * 响应码
     */
    private Integer resultCode;

    /**
     * 响应消息
     */
    private String resultMsg;

    /**
     * 响应业务数据
     */
    private T data;

}
