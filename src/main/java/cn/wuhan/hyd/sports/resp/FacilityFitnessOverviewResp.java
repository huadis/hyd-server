package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 健身点位 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Data
public class FacilityFitnessOverviewResp implements Serializable {

    @ApiModelProperty(value = "健身点位数量")
    private String quantity;

    @ApiModelProperty(value = "本年巡检率")
    private String yearInspectRate;

    @ApiModelProperty(value = "本年已巡检")
    private String yearInspectYes;

    @ApiModelProperty(value = "本年待巡检")
    private String yearInspectNo;

    @ApiModelProperty(value = "本年维修完成率")
    private String yearRepairRate;

    @ApiModelProperty(value = "本年已维修")
    private String yearRepairYes;

    @ApiModelProperty(value = "本年待维修")
    private String yearRepairNo;
}
