package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育消费卷 - 卷面统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月28日 <br>
 */
@Data
@AllArgsConstructor
public class SportsCouponStatsDTO implements Serializable {

    // 券面额（10元、20元等）
    @ApiModelProperty(value = "券面额")
    private String denomination;
    // 领券人数
    @ApiModelProperty(value = "领券人数")
    private Integer receiveCount;
    // 用券人数
    @ApiModelProperty(value = "用券人数")
    private Integer useCount;
    // 使用率
    @ApiModelProperty(value = "使用率")
    private Double usageRate;
}
