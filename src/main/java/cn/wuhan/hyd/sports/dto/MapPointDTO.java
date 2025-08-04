package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育基础设施 - 地图点位数据  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月29日 <br>
 */
@Data
@AllArgsConstructor
@ApiModel("地图点位数据")
public class MapPointDTO implements Serializable {
    @ApiModelProperty("设施名称")
    private String name;

    @ApiModelProperty("纬度")
    private Double lat;

    @ApiModelProperty("经度")
    private Double lng;
}
