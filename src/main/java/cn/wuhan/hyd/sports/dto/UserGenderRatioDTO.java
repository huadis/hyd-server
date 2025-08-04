package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 场馆预定: 用户性别占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class UserGenderRatioDTO implements Serializable {
    // 男性占比
    @ApiModelProperty(value = "男性占比")
    private Double maleRatio;
    // 男性人数
    @ApiModelProperty(value = "男性人数")
    private Integer maleCount;
    // 女性占比
    @ApiModelProperty(value = "女性占比")
    private Double femaleRatio;
    // 女性人数
    @ApiModelProperty(value = "女性人数")
    private Integer femaleCount;
}
