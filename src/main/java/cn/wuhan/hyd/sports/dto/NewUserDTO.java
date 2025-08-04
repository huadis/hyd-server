package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 场馆预定: 新增用户统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class NewUserDTO implements Serializable {

    // 月份列表，如 ["1月", "2月"...]
    @ApiModelProperty(value = "月份列表")
    private List<String> months;
    // 对应月份的新增用户数列表
    @ApiModelProperty(value = "对应月份的新增用户数列表")
    private List<Integer> newUserCounts;
    // 对应月份的用户增长率列表
    @ApiModelProperty(value = "对应月份的用户增长率列表")
    private List<Double> growthRates;
}
