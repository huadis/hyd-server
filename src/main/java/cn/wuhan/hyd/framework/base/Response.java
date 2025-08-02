package cn.wuhan.hyd.framework.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明: 接口响应体 <br>
 * 开发人员: @author huadi<br>
 * 开发时间: 2020年02月04日<br>
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    @ApiModelProperty(value = "是否成功")
    private boolean success = true;
    @ApiModelProperty(value = "返回对象")
    private T result;
    @ApiModelProperty(value = "编码")
    private Integer status;
    @ApiModelProperty(value = "错误信息")
    private String message;

    /**
     * 成功
     *
     * @param <T>
     * @return
     */
    public static <T> Response<T> ok() {
        return Response.build(200, "成功", null);
    }

    /**
     * 成功
     *
     * @param result 结果
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> ok(T result) {
        return Response.build(200, "成功", result);
    }

    /**
     * 快速响应
     *
     * @param flag 标志
     * @return 响应
     */
    public static Response<String> flag(boolean flag) {
        if (flag) {
            return Response.ok("成功");
        } else {
            return Response.fail("失败");
        }
    }

    /**
     * 快速响应
     *
     * @param flag 标志
     * @return 响应
     */
    public static Response<String> flag(boolean flag, String message) {
        if (flag) {
            return Response.ok(message + "成功");
        } else {
            return Response.fail(message + "失败");
        }
    }

    /**
     * 成功
     *
     * @param message 成功消息
     * @param result  结果
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> ok(String message, T result) {
        return Response.build(200, message, result);
    }

    /**
     * 失败
     *
     * @param message 失败原因
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> fail(String message) {
        return build(500, message, null);
    }

    /**
     * 失败
     *
     * @param code    失败码
     * @param message 失败原因
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> fail(Integer code, String message) {
        return build(code, message, null);
    }

    /**
     * 失败
     *
     * @param message 失败原因
     * @param result  失败结果
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> fail(String message, T result) {
        return build(500, message, result);
    }

    /**
     * 无权限访问
     *
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> unAuthorized() {
        return build(401, "无权限访问", null);
    }

    /**
     * 设置分页结果
     *
     * @param t         内容
     * @param total     总条数
     * @param totalPage 总页数
     * @return 结果
     */
    public static Response<Map<String, Object>> setTableData(Object t, Long total, Integer totalPage) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("content", t);
        result.put("total", total == null ? 0 : total);
        result.put("totalPage", totalPage == null ? 0 : totalPage);
        return Response.ok("查询成功", result);
    }

    /**
     * 构建响应体
     *
     * @param status  状态码
     * @param message 消息体
     * @param result  数据
     * @author huadi
     * @date 2019-03-17
     */
    public static <T> Response<T> build(Integer status, String message, T result) {
        Response<T> model = new Response<>();
        return model.setResult(result).setSuccess(status == 200).setStatus(status).setMessage(message);
    }


    public boolean isSuccess() {
        return success;
    }

    public Response<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getResult() {
        return result;
    }

    public Response<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Response<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }
}
