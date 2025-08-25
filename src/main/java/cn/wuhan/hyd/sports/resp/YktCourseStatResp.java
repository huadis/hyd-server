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
public class YktCourseStatResp implements Serializable {

    @ApiModelProperty(value = "课程名称，如青少年篮球基础课")
    private String course;

    @ApiModelProperty(value = "课程热度统计数量（如报名人数、订单数）")
    private Long num;
}
