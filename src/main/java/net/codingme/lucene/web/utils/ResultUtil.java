package net.codingme.lucene.web.utils;


import net.codingme.lucene.web.model.Result;

/**
 * 统一的返回值处理
 * @Author DarcyNiu
 * @date 2018/4/7 22:20
 */
public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("success");
        result.setView(object);
        return  result;
    }

    public static  Result success(){
        return success(null);
    }

    public static Result error(Integer code , String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
