package com.gdz.demo.annotation;

import java.lang.annotation.*;

/**
 * @Author: guandezhi
 * @Date: 2019/12/17 16:55
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidate {
}
