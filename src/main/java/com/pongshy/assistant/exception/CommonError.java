package com.pongshy.assistant.exception;

import org.springframework.http.HttpStatus;

/**
 * @ClassName: CommonError
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 19:02
 **/
public interface CommonError {


    Integer getErrCode();

    String getMsg();

    HttpStatus getHttpStatus();

    CommonError setErrMsg(String errMsg);
}
