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
public class CouponAmountOverviewResp implements Serializable {

    @ApiModelProperty(value = "发放金额")
    private String sendAmount;

    @ApiModelProperty(value = "领券人次")
    private String receiveCount;

    @ApiModelProperty(value = "用券人次")
    private String usedCount;

    @ApiModelProperty(value = "用券资金占比")
    private String usedRatio;

    @ApiModelProperty(value = "用券金额")
    private String useCouponAmount;

    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    @ApiModelProperty(value = "拉动消费比")
    private String orderRatio;
}
