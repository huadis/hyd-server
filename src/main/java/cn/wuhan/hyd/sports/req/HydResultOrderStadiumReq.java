package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-体育消费卷-场馆消费券订单金额Top5 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultOrderStadiumReq implements Serializable {

    @ApiModelProperty(value = "场馆名称")
    private String stadiumName;

    @ApiModelProperty(value = "消费券订单金额")
    private String orderAmount;
}
