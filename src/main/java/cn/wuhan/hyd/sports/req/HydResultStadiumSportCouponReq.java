package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-体育消费卷/场馆预定-运动项目分布用券数占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultStadiumSportCouponReq implements Serializable {

    @ApiModelProperty(value = "运动项目")
    private String sportName;

    @ApiModelProperty(value = "项目数量")
    private String sportNum;

    @ApiModelProperty(value = "用券占比")
    private String useCountRate;
}
