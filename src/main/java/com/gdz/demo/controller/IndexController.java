package com.gdz.demo.controller;

import com.gdz.demo.annotation.ParamValidate;
import com.gdz.demo.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guandezhi
 * @Date: 2020/1/17 15:04
 */
@Slf4j
@RestController
public class IndexController {

    @PostMapping("/index")
    public String test(@RequestBody @ParamValidate User user) {
        return "success";
    }
}
