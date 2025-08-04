package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 场馆预定: 订单趋势 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class OrderTrendDTO implements Serializable {

    // 月份列表，如 ["1月", "2月"...]
    @ApiModelProperty(value = "月份列表")
    private List<String> months;
    // 对应月份的订单总数列表
    @ApiModelProperty(value = "对应月份的订单总数列表")
    private List<Integer> orderCounts;
    // 对应月份的订单总金额列表（单位：万元）
    @ApiModelProperty(value = "对应月份的订单总金额列表")
    private List<Double> orderAmounts;
}
