package cn.wuhan.hyd.sports.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能说明： 场馆预定: 项目场馆数据统计 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月27日 <br>
 */
@Data
public class ProjectVenueDTO implements Serializable {
    // 项目名称列表，如 ["乒乓球", "羽毛球"...]
    @ApiModelProperty(value = "项目名称列表")
    private List<String> projectNames;
    // 对应项目的场馆数量（或订单数等，按需调整）
    @ApiModelProperty(value = "对应项目的场馆数量")
    private List<Integer> venueOrOrderCounts;
}
