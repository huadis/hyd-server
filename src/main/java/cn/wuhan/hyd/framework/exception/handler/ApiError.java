package cn.wuhan.hyd.framework.exception.handler;

import lombok.Data;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月13日 <br>
 */
@Data
public class ApiError {

    private Integer status = 400;
    private Long timestamp;
    private String message;

    private ApiError() {
        timestamp = System.currentTimeMillis();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }
}
