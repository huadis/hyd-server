package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-体育消费卷-消费券领用券 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultStockReq implements Serializable {

    @ApiModelProperty(value = "券名称")
    private String stockName;

    @ApiModelProperty(value = "领券数")
    private String receiveNum;

    @ApiModelProperty(value = "用券数")
    private String useNum;

    @ApiModelProperty(value = "用券率")
    private String useRate;

    @ApiModelProperty(value = "用券金额")
    private String useCouponAmount;

    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    @ApiModelProperty(value = "拉动消费比")
    private String orderRate;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "分组id")
    private String groupId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "类型")
    private String type;
}
