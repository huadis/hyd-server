package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 场馆预定: 各区场馆数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class StadiumDistrictCountResp implements Serializable {
    @ApiModelProperty(value = "区名称")
    private String districtName;
    @ApiModelProperty(value = "对应区的场馆数量")
    private String stadiumNum;
}
