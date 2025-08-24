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
public class StockUseOrderStatResp implements Serializable {

    @ApiModelProperty(value = "券名称")
    private String stockName;

    @ApiModelProperty(value = "用券金额")
    private String useCouponAmount;

    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    @ApiModelProperty(value = "拉动消费比")
    private String orderRate;
}
