package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-体育基础设施-电子地图 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultStadiumMapReq implements Serializable {

    @ApiModelProperty(value = "资源类型")
    private String resourceType;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源数量")
    private String resourceNum;
}
