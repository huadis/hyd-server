package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-年龄占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultUserAgeReq implements Serializable {

    @ApiModelProperty(value = "18岁以下")
    private String under18Num;

    @ApiModelProperty(value = "18-25岁")
    private String bt18and25Num;

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

    @ApiModelProperty(value = "50岁以上")
    private String over50Num;

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
