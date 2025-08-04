package cn.wuhan.hyd.sports.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 场馆预定: 用户性别统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class StadiumUserSexStatResp implements Serializable {
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "数量")
    private Integer genderCount;
}
