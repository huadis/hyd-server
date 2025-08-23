package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：体育基础设施-设施全貌 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultFacilityReq implements Serializable {

    @ApiModelProperty(value = "设施类型名称")
    private String facilityTypeName;

    @ApiModelProperty(value = "设施数量")
    private String facilityNum;
}
