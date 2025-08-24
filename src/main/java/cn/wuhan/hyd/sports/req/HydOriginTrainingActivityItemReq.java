package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：培训活动支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginTrainingActivityItemReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "项目类型编码")
    private String sportCode;

    @ApiModelProperty(value = "项目类型名称")
    private String sportName;

    @ApiModelProperty(value = "培训对象年龄起始限制")
    private String ageStartLimit;

    @ApiModelProperty(value = "培训对象年龄结束限制")
    private String ageEndLimit;

    @ApiModelProperty(value = "状态（0-启用，1-停用 ）")
    private Integer disabled;  // 表中TINYINT类型，用Integer接收

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
