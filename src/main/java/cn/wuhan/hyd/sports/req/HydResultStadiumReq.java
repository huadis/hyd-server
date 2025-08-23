package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-在线场馆数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultStadiumReq implements Serializable {

    @ApiModelProperty(value = "在线场馆数量")
    private String onlineStadiumNum;

    @ApiModelProperty(value = "消费券场馆数量")
    private String couponStadiumNum;

    @ApiModelProperty(value = "社会场馆数量")
    private String socialStadiumNum;

    @ApiModelProperty(value = "公共场馆数量")
    private String publicStadiumNum;

    @ApiModelProperty(value = "累计用券场馆数量")
    private String useCouponStadiumNum;
}
