package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 场馆预定: 用户渠道统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class StadiumUserAgeStatResp implements Serializable {
    @ApiModelProperty(value = "18岁以下")
    private Integer under18Count;
    @ApiModelProperty(value = "18-25岁")
    private Integer bt18and25Count;
    @ApiModelProperty(value = "26-30岁")
    private Integer bt26and30Count;
    @ApiModelProperty(value = "31-35岁")
    private Integer bt31and35Count;
    @ApiModelProperty(value = "36-40岁")
    private Integer bt36and40Count;
    @ApiModelProperty(value = "41-45岁")
    private Integer bt41and45Count;
    @ApiModelProperty(value = "46-50岁")
    private Integer bt46and50Count;
    @ApiModelProperty(value = "50岁以上")
    private Integer over50Count;
}
