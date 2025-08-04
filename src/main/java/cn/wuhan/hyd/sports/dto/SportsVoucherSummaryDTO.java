package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育消费卷 - 概览 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月28日 <br>
 */
@Data
public class SportsVoucherSummaryDTO implements Serializable {

    @ApiModelProperty(value = "发放金额")
    private Double issueAmount;
    @ApiModelProperty(value = "领卷人次")
    private Double receivePersonTimes;
    @ApiModelProperty(value = "用卷人次")
    private Double usePersonTimes;
    @ApiModelProperty(value = "用卷金额")
    private Double useAmount;
    @ApiModelProperty(value = "订单金额")
    private Double orderAmount;
    @ApiModelProperty(value = "用卷资金占比")
    private Double useFundsRatio;
    @ApiModelProperty(value = "消费拉动比")
    private Double consumptionPullRatio;
}
