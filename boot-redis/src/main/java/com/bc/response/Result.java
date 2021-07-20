package com.bc.response;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class Result<T> {
    private Integer code = HttpStatus.OK.value();
    private String msg = "success";
    private T data;
    private Long total;

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result() {
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(T data, Long total){
        this.data = data;
        this.total = total;
    }

    public static Result ok() {
        return new Result();
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> ok(T data, Long total) {
        return new Result<>(data, total);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg);
    }

}
