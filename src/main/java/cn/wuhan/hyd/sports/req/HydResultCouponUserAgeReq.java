package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-体育消费卷-券用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultCouponUserAgeReq implements Serializable {

    @ApiModelProperty(value = "25岁以下")
    private String under25Num;

    @ApiModelProperty(value = "26-30岁")
    private String bt26and30Num;

    @ApiModelProperty(value = "31-35岁")
    private String bt31and35Num;

    @ApiModelProperty(value = "36-40岁")
    private String bt36and40Num;

    @ApiModelProperty(value = "41-45岁")
    private String bt41and45Num;

    @ApiModelProperty(value = "46-50岁")
    private String bt46and50Num;

    @ApiModelProperty(value = "51-60岁")
    private String bt51and60Num;

    @ApiModelProperty(value = "60岁以上")
    private String over60Num;

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





