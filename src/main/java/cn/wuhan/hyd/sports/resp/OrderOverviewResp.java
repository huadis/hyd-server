package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Data
public class OrderOverviewResp implements Serializable {

    @ApiModelProperty(value = "订单总数")
    private String orderNum;

    @ApiModelProperty(value = "订单总金额")
    private String orderAmount;

    @ApiModelProperty(value = "消费券总金额")
    private String couponAmount;
}
