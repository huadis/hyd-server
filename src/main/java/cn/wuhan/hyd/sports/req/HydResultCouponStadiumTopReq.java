package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定/体育消费卷-消费券场馆预订Top <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultCouponStadiumTopReq implements Serializable {

    @ApiModelProperty(value = "场馆名称")
    private String stadiumName;

    @ApiModelProperty(value = "消费券金额")
    private String couponAmount;

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
