package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-订单数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultOrderReq implements Serializable {

    @ApiModelProperty(value = "订单总数")
    private String orderNum;

    @ApiModelProperty(value = "订单总金额")
    private String orderAmount;

    @ApiModelProperty(value = "消费券总金额")
    private String couponAmount;
}
