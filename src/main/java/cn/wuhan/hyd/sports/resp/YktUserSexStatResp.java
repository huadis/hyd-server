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
public class YktUserSexStatResp implements Serializable {

    @ApiModelProperty(value = "性别，如男、女")
    private String gender;

    @ApiModelProperty(value = "对应性别的统计数量")
    private Long num;
}
