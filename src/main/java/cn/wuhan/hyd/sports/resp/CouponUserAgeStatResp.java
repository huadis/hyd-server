package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Data
public class CouponUserAgeStatResp implements Serializable {

    @ApiModelProperty(value = "25岁以下")
    private String under25Num;

    @ApiModelProperty(value = "26-30岁")
    private String bt26and30Num;

    @ApiModelProperty(value = "31-35岁")
    private String bt31and35Num;

    @ApiModelProperty(value = "36-40岁")
    private String bt36and40Num;

    @ApiModelProperty(value = "41-45岁")
    private String bt41and45Num;

    @ApiModelProperty(value = "46-50岁")
    private String bt46and50Num;

    @ApiModelProperty(value = "51-60岁")
    private String bt51and60Num;

    @ApiModelProperty(value = "60岁以上")
    private String over60Num;
}
