package com.util;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 现仔
 * @Description:
 */
import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

//统一返回结果的类
@Data
public class Result<T> {

    private Boolean success;//是否成功

    private Integer code;//返回码

    private String message;//返回消息

    private T data;//定义泛型，用于接受数据。

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public Result(){}

    public Result(T data) {
        this.data = data;
    }
    public static Result success(){
        Result result = new Result<>();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
