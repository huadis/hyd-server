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
public class StadiumUserChannelStatResp implements Serializable {
    @ApiModelProperty(value = "实名用户数")
    private String realNameUserCount;
    @ApiModelProperty(value = "领券用户数")
    private Integer receiveCouponUserCount;
    @ApiModelProperty(value = "用券用户数")
    private Integer useCouponUserCount;
    @ApiModelProperty(value = "下单用户数")
    private Integer orderUserCount;
}
