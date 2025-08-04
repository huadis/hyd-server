package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育基础设施 - 设施概览数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月29日 <br>
 */
@Data
@ApiModel("设施概览数据")
public class FacilityOverviewDTO implements Serializable {
    @ApiModelProperty("健身点位总数")
    private Integer totalPoints = 28739;

    @ApiModelProperty("年度巡检率")
    private String inspectionRate = "96%";

    @ApiModelProperty("年度维修完成率")
    private String repairRate = "91%";

    @ApiModelProperty("已巡检数量")
    private Integer inspectedCount = 28739;

    @ApiModelProperty("已维修数量")
    private Integer repairedCount = 249;

    @ApiModelProperty("待巡检数量")
    private Integer toInspectCount = 1101;

    @ApiModelProperty("待维修数量")
    private Integer toRepairCount = 19;
}
