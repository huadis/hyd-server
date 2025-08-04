package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 场馆预定: 用户增长 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class StadiumUserGrowthStatResp implements Serializable {
    @ApiModelProperty(value = "月份")
    private String monthName;
    @ApiModelProperty(value = "新增用户数")
    private Integer newUserCount;
}
