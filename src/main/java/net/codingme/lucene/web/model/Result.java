package net.codingme.lucene.web.model;

/**
 * Http请求的最外层对象
 * @Author DarcyNiu
 * @date 2018/4/7 22:09
 */
public class Result<T> {

    /** 错误码 **/
    private Integer code;

    /** 提示信息 **/
    private String msg;

    /** 具体的内容 **/
    private  T view;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", view=" + view +
                '}';
    }
}
