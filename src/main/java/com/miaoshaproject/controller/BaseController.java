package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    //定义exceptionhander解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handerException(HttpServletRequest request, Exception ex)
    {
        Map<String,Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        } else {
            responseData.put("errCode", EmBusinessError.UNKOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData, "fail");
    }
}
