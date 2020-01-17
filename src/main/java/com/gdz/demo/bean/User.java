package com.gdz.demo.bean;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @Author: guandezhi
 * @Date: 2020/1/17 15:05
 */
@Data
public class User {

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotBlank
    private String city;

    @Min(value = 1, message = "年龄最小值不能小于1")
    @Max(value = 99, message = "年龄最大值不能大于99")
    private int age;

    @Size(min = 1, max = 3, message = "别名不能少于1个，大于3个")
    private List<String> alias;
}
