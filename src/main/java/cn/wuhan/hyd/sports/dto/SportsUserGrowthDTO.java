package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月29日 <br>
 */
@Data
@AllArgsConstructor
public class SportsUserGrowthDTO implements Serializable {
    // 月份
    @ApiModelProperty(value = "月份")
    private String month;
    // 用户总数
    @ApiModelProperty(value = "用户总数")
    private Integer totalUser;
    // 新增用户
    @ApiModelProperty(value = "新增用户")
    private Integer newUser;
    // 用户增长率
    @ApiModelProperty(value = "用户增长率")
    private Double growthRate;

}
