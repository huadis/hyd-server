package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育基础设施 - 各区设施分布  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月29日 <br>
 */
@Data
@ApiModel("各区设施分布")
public class DistrictDistributionDTO implements Serializable {
    @ApiModelProperty("行政区名称")
    private String district;

    @ApiModelProperty("设施数量")
    private Integer count;

    public DistrictDistributionDTO(String district, Integer count) {
        this.district = district;
        this.count = count;
    }
}
