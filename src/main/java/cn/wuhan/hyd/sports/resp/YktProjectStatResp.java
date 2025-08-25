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
public class YktProjectStatResp implements Serializable {

    @ApiModelProperty(value = "培训项目名称，如篮球、编程")
    private String project;

    @ApiModelProperty(value = "对应项目的机构数量")
    private Long num;
}
