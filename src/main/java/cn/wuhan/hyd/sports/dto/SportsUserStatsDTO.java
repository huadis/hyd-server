package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 体育消费卷 - 用户统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月28日 <br>
 */
@Data
@NoArgsConstructor
public class SportsUserStatsDTO implements Serializable {
    // 男性人数
    @ApiModelProperty(value = "男性人数")
    private Integer maleCount;
    // 女性人数
    @ApiModelProperty(value = "女性人数")
    private Integer femaleCount;
    // 各年龄段占比（如 "18岁以下:30,19-25岁:50"）
    @ApiModelProperty(value = "各年龄段占比")
    private String ageDistribution;
    @ApiModelProperty(value = "用户增长统计")
    private List<SportsUserGrowthDTO> mouthGrowthStats;
}
