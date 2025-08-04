package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育基础设施 - 巡检维修记录  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月29日 <br>
 */
@Data
@AllArgsConstructor
@ApiModel("巡检维修记录")
public class InspectionRecordDTO implements Serializable {
    @ApiModelProperty("设施编号")
    private String code;

    @ApiModelProperty("所属区县")
    private String district;

    @ApiModelProperty("所属街道")
    private String road;

    @ApiModelProperty("安装点位名称")
    private String address;

    @ApiModelProperty("巡检人姓名")
    private String inspector;

    @ApiModelProperty("巡检状态")
    private String status;

    @ApiModelProperty("巡检时间")
    private String time;
}
