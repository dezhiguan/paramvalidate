package com.gdz.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum HttpCode {
    SUCCESS(100, "请求成功"), FAIL(300, "请求错误"), ERROR(500, "系统繁忙，请稍后再试"), ILLEGAL_INPUT(401, "非法输入"),;

    private Integer msgCode;
    private String message;
}
