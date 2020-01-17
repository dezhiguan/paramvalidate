package com.gdz.demo.handler;


import com.gdz.demo.bean.HttpCode;
import com.gdz.demo.exception.ParamValidateException;
import com.gdz.demo.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: guandezhi
 * @Date: 2019/12/17 16:53
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVo<Object> handleException(Exception e) {
        String errorMsg = "";
        ResultVo<Object> resultVo = new ResultVo<>();

        if (e instanceof NullPointerException) {
            errorMsg = "参数空指针异常";
            resultVo.setResultCode(HttpCode.FAIL.getMsgCode());
        } else if (e instanceof ParamValidateException) {
            errorMsg = "请求参数匹配错误," + e.getLocalizedMessage();
            resultVo.setResultCode(HttpCode.ILLEGAL_INPUT.getMsgCode());
        } else {
            errorMsg = e.getMessage();
            resultVo.setResultCode(HttpCode.ERROR.getMsgCode());
            log.error("系统异常：", e);
        }
        resultVo.setResultMsg(errorMsg);
        return resultVo;
    }
}
