package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 体育消费卷 - 订单统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月28日 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportsOrderStatsDTO implements Serializable {
    // 订单数
    @ApiModelProperty(value = "订单总数")
    private Integer orderCount;
    // 订单总金额
    @ApiModelProperty(value = "订单总金额")
    private Double totalAmount;
    // 消费券总金额
    @ApiModelProperty(value = "订单消费券总金额")
    private Double couponAmount;
    // 券面额统计（同 CouponStats）
    @ApiModelProperty(value = "券面额统计")
    private List<SportsCouponStatsDTO> couponStats;
}
