package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 场馆预定: 复购率相关 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class RePurchaseDTO implements Serializable {
    // 不同复购率区间占比，如 ["0-20%", "20-40%"...]
    @ApiModelProperty(value = "不同复购率区间占比")
    private List<String> rateRanges;
    // 对应区间的用户占比列表
    @ApiModelProperty(value = "对应区间的用户占比列表")
    private List<Double> ratios;
}
