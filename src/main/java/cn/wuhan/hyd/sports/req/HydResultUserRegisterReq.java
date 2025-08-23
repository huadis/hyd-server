package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-每月新增用户 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultUserRegisterReq implements Serializable {

    @ApiModelProperty(value = "月份")
    private String month;

    @ApiModelProperty(value = "数量")
    private String userNum;
}
