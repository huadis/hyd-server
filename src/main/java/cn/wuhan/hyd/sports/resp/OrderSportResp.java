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
public class OrderSportResp implements Serializable {

    @ApiModelProperty(value = "项目名称")
    private String sportName;

    @ApiModelProperty(value = "消费券订单金额")
    private String orderAmount;
}
