package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 场馆预定: 场馆分布地图数据  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class VenueMapDTO implements Serializable {
    // 场馆名称
    @ApiModelProperty(value = "场馆名称")
    private String name;
    // 场馆地址
    @ApiModelProperty(value = "场馆地址")
    private String address;
    // 预定金额（单位：万）
    @ApiModelProperty(value = "预定金额（单位：万）")
    private Double preAmount;
    // 订单笔数
    @ApiModelProperty(value = "订单笔数")
    private Integer orderCount;
}
