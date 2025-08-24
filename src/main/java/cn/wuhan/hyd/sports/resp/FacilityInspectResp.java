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
public class FacilityInspectResp implements Serializable {

    @ApiModelProperty(value = "设施点编号")
    private String code;

    @ApiModelProperty(value = "所属区县")
    private String districtName;

    @ApiModelProperty(value = "所属街道")
    private String streetName;

    @ApiModelProperty(value = "安装点名称")
    private String location;

    @ApiModelProperty(value = "巡检单类型")
    private String type;

    @ApiModelProperty(value = "巡检人名称")
    private String inspector;

    @ApiModelProperty(value = "巡检完成状态")
    private String status;

    @ApiModelProperty(value = "巡检完成时间")
    private String completeTime;
}
