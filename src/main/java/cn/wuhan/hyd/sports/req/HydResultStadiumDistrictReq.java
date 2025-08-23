package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-在线场馆各区情况 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultStadiumDistrictReq implements Serializable {

    @ApiModelProperty(value = "所属区")
    private String district;

    @ApiModelProperty(value = "所属区名称")
    private String districtName;

    @ApiModelProperty(value = "定点场馆数量")
    private String couponStadiumNum;

    @ApiModelProperty(value = "公共场馆数量")
    private String publicStadiumNum;

    @ApiModelProperty(value = "社会场馆数量")
    private String socialStadiumNum;
}
