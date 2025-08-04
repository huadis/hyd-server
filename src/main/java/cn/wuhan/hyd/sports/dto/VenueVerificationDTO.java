package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明： 体育消费卷 - 场馆核销  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月28日 <br>
 */
@Data
@AllArgsConstructor
public class VenueVerificationDTO implements Serializable {
    // 项目（乒乓球、滑冰等）
    @ApiModelProperty(value = "项目")
    private String project;
    // 核销金额
    @ApiModelProperty(value = "核销金额")
    private Double amount;
    // 场馆名称
    @ApiModelProperty(value = "场馆名称")
    private String venue;
}
