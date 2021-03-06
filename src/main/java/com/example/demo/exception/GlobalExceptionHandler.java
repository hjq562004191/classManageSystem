package com.example.demo.exception;


import com.example.demo.model.ResultModel;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultModel handler(Exception e) {
        logger.error("发生异常：" + e.getClass().getName());
        logger.error("信息：" + e.getMessage());
        logger.error("原因：" + e.getCause());
        e.printStackTrace();
        ResultModel result = new ResultModel();
        result.setStatus(500);
        result.setMessage(e.getMessage());
        return result;
    }
}
