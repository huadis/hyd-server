package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Data
public class FacilityDistrictMonthResp implements Serializable {

    @ApiModelProperty(value = "区名称")
    private String districtName;

    @ApiModelProperty(value = "已巡检")
    private String monthInspectYes;

    @ApiModelProperty(value = "待巡检")
    private String monthInspectNo;
}
