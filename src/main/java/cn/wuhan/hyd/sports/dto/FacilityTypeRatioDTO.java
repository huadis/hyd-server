package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育基础设施 - 设施类型占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月29日 <br>
 */
@Data
@ApiModel("设施类型占比")
public class FacilityTypeRatioDTO implements Serializable {
    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("占比（百分比）")
    private Integer ratio;

    public FacilityTypeRatioDTO(String typeName, Integer ratio) {
        this.typeName = typeName;
        this.ratio = ratio;
    }
}
