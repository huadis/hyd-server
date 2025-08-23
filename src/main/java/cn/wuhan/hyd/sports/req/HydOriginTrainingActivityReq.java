package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：培训活动表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginTrainingActivityReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;  // 注意这里主键类型是String，和表结构一致，不同于模板里的Long

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "主办单位")
    private String organizingCompany;

    @ApiModelProperty(value = "活动开始时间")
    private Timestamp startTime;  // 表结构是DATETIME，对应java.sql.Timestamp

    @ApiModelProperty(value = "活动结束时间")
    private Timestamp endTime;

    @ApiModelProperty(value = "启用开关（0-关闭，1-打开 ）")
    private Integer open;  // 表结构TINYINT，用Integer接收

    @ApiModelProperty(value = "状态（0-启用，1-停用 ）")
    private Integer disabled;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
