package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：体育基础设施-设施各区月数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultFacilityDistrictMonthReq implements Serializable {

    @ApiModelProperty(value = "区名称")
    private String districtName;

    @ApiModelProperty(value = "本月已巡检")
    private String monthInspectYes;

    @ApiModelProperty(value = "本月待巡检")
    private String monthInspectNo;

    @ApiModelProperty(value = "本月已维修")
    private String monthRepairYes;

    @ApiModelProperty(value = "本月待维修")
    private String monthRepairNo;
}
