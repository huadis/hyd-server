package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Data
public class CouponGetAndUseStatResp implements Serializable {

    @ApiModelProperty(value = "领券人数")
    private String receiveCouponNum;

    @ApiModelProperty(value = "用券人数")
    private String useCouponNum;
}
