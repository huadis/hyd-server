package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：培训课程表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginTrainingCourseReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @ApiModelProperty(value = "项目id")
    private String stadiumItemId;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "开课时间")
    private Long startTime;

    @ApiModelProperty(value = "结课时间")
    private Long endTime;

    @ApiModelProperty(value = "报名开始时间")
    private Long registrationStartTime;

    @ApiModelProperty(value = "报名结束时间")
    private Long registrationEndTime;

    @ApiModelProperty(value = "限报人数限制")
    private Integer headCountLimit;

    @ApiModelProperty(value = "课程时长，单位分钟")
    private Integer durationLimit;

    @ApiModelProperty(value = "限报人数限制")
    private Integer totalCountLimit;

    @ApiModelProperty(value = "培训对象年龄起始")
    private String ageStartLimit;

    @ApiModelProperty(value = "培训对象年龄结束")
    private String ageEndLimit;

    @ApiModelProperty(value = "价格，单位分")
    private Integer price;

    @ApiModelProperty(value = "小程序上下线")
    private Integer hydOnline;

    @ApiModelProperty(value = "状态，0-启用，1-停用")
    private Integer disabled;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
