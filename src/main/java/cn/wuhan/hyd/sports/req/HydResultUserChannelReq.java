package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-用户来源渠道 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultUserChannelReq implements Serializable {

    @ApiModelProperty(value = "注册用户")
    private String registerUserNum;

    @ApiModelProperty(value = "实名用户")
    private String realNameUserNum;

    @ApiModelProperty(value = "领券用户")
    private String receiveCouponUserNum;

    @ApiModelProperty(value = "用券用户")
    private String useCouponUserNum;

    @ApiModelProperty(value = "下单用户")
    private String orderUserNum;
}
