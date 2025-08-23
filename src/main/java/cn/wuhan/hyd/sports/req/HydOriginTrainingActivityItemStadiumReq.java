package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：培训活动场馆支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginTrainingActivityItemStadiumReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @ApiModelProperty(value = "项目类型编码")
    private String sportCode;

    @ApiModelProperty(value = "项目类型名称")
    private String sportName;
}
