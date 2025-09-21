package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 设施全貌 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Data
public class FacilityOverviewResp implements Serializable {

    @ApiModelProperty(value = "设施类型名称")
    private String facilityTypeName;

    @ApiModelProperty(value = "设施数量")
    private String facilityNum;

    @ApiModelProperty(value = "设施占比（%）")
    private Double facilityPercentage;
}
