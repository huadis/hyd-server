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
public class YktStadiumStatResp implements Serializable {

    @ApiModelProperty(value = "培训场馆名称，如武汉体育中心")
    private String stadium;

    @ApiModelProperty(value = "场馆销售金额统计")
    private Integer orderAmount;
}
