package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginStadiumItemReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @ApiModelProperty(value = "项目名称")
    private String stadiumItemName;

    @ApiModelProperty(value = "项目类型编码")
    private String sportCode;

    @ApiModelProperty(value = "项目类型名称")
    private String sportName;

    @ApiModelProperty(value = "状态（0-启用，1-停用 ）")
    private Integer disabled;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
