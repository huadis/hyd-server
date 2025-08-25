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
public class YktDistrictStatResp implements Serializable {

    @ApiModelProperty(value = "所属区编码")
    private String district;

    @ApiModelProperty(value = "所属区名称")
    private String districtName;

    @ApiModelProperty(value = "各区对应的机构数量")
    private Long num;
}
