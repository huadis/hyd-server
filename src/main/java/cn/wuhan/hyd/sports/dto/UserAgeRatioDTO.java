package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 场馆预定: 用户年龄占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class UserAgeRatioDTO implements Serializable {
    // 年龄区间，如 ["18岁以下", "19-25岁"...]
    @ApiModelProperty(value = "年龄区间")
    private List<String> ageRanges;
    // 对应年龄区间的占比列表
    @ApiModelProperty(value = "对应年龄区间的占比列表")
    private List<Double> ageRatios;
}
