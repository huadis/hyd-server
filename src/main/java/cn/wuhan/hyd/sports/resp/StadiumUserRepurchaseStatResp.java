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
public class StadiumUserRepurchaseStatResp implements Serializable {
    @ApiModelProperty(value = "一次下单")
    private String onceCount;
    @ApiModelProperty(value = "2-5次")
    private String bt2and5Count;
    @ApiModelProperty(value = "5次以上")
    private String over5Count;
    @ApiModelProperty(value = "10次以上")
    private String over10Count;
    @ApiModelProperty(value = "50次以上")
    private String over50Count;
}
