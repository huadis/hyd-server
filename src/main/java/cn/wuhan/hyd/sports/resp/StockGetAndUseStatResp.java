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
public class StockGetAndUseStatResp implements Serializable {

    @ApiModelProperty(value = "券名称")
    private String stockName;

    @ApiModelProperty(value = "领券数")
    private String receiveNum;

    @ApiModelProperty(value = "用券数")
    private String useNum;

    @ApiModelProperty(value = "用券率")
    private String useRate;
}
