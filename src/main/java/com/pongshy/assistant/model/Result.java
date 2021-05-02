package com.pongshy.assistant.model;

import com.pongshy.assistant.exception.AllException;
import com.pongshy.assistant.tool.TimeTool;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @ClassName: Result
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 19:01
 **/
@Data
public class Result<T> {


    private String timestamp;

    private Integer code;

    private String message;

    private T data;

    public Result() {

    }


    public Result(HttpStatus status,
                  String msg
    ) {
        Date date = new Date(System.currentTimeMillis());

        this.timestamp = TimeTool.DateToString(date);
        this.code = status.value();
        this.message = msg;
    }

    public Result(AllException ex) {
        Date date = new Date(System.currentTimeMillis());

        this.timestamp = TimeTool.DateToString(date);
        this.code = ex.getErrCode();
        this.message = ex.getMsg();
    }

    public static Result success(Object object){
        Result result = new Result(HttpStatus.OK, "");
        result.setData(object);
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result(HttpStatus.OK, msg);
        return result;
    }

    public static Result error(AllException ex){
        return new Result(ex);
    }

    public static Result error(String msg, Integer code) {
        Result result = new Result();

        result.setMessage(msg);
        result.setCode(code);
        result.setTimestamp(TimeTool.DateToString(new Date(System.currentTimeMillis())));
        result.setData(null);
        return result;
    }


}
