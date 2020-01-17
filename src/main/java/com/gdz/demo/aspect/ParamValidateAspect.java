package com.gdz.demo.aspect;

import com.gdz.demo.annotation.ParamValidate;
import com.gdz.demo.exception.ParamValidateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

/**
 * 参数校验切面
 *
 * @Author: guandezhi
 * @Date: 2019/12/17 15:07
 */
@Slf4j
@Aspect
@Component
public class ParamValidateAspect {

    @Resource
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    /**
     * 切入controller的方法参数加了自定义注解ParamValidate的方法
     */
    @Pointcut("execution(public * com.gdz.demo.controller.*." +
            "*(@com.gdz.demo.annotation.ParamValidate (*),..)) ||" +
            " execution(public * com.gdz.demo.controller.*." +
            "*(..,@com.gdz.demo.annotation.ParamValidate (*)))")

    public void webLog() {
    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) throws Exception {
        doBefore(joinPoint);
    }

    /**
     * 参数校验
     *
     * @param joinPoint
     */
    private void doBefore(JoinPoint joinPoint) {
        Object paramValue = getMethodParamValue(joinPoint);
        if (paramValue != null) {
            Set<ConstraintViolation<Object>> validErrors = this.localValidatorFactoryBean.validate(paramValue, new Class[]{Default.class});
            Iterator iterator = validErrors.iterator();
            StringBuilder errorMsg = new StringBuilder();

            while (iterator.hasNext()) {
                ConstraintViolation constraintViolation = (ConstraintViolation) iterator.next();
                String error = constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage();
                errorMsg.append(iterator.hasNext() ? error + "; " : error);
            }
            if (!validErrors.isEmpty()) {
                throw new ParamValidateException(errorMsg.toString());
            }
        }
    }


    /**
     * 获取加了自定义参数校验注解的参数值
     *
     * @param joinPoint
     * @return
     */
    private Object getMethodParamValue(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        /*获取@ParamValidate注解*/
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        if (parameterAnnotations != null) {
            for (Annotation[] parameterAnnotation : parameterAnnotations) {
                int paramIndex = ArrayUtils.indexOf(parameterAnnotations, parameterAnnotation);
                for (Annotation annotation : parameterAnnotation) {
                    if (annotation != null && annotation instanceof ParamValidate) {
                        return args[paramIndex];
                    }
                }
            }
        }
        return null;
    }
}
