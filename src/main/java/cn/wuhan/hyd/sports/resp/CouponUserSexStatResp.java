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
public class CouponUserSexStatResp implements Serializable {

    @ApiModelProperty(value = "男性人数")
    private String maleNum;

    @ApiModelProperty(value = "男性占比")
    private String maleRatio;

    @ApiModelProperty(value = "女性人数")
    private String femaleNum;

    @ApiModelProperty(value = "女性占比")
    private String femaleRatio;
}
