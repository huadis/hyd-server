package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：校外培训机构
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginLaStadiumReq implements Serializable {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "营业类型")
    private String certBus;

    @ApiModelProperty(value = "门头照片")
    private String doorImages;

    @ApiModelProperty(value = "营业时间")
    private String busHours;

    @ApiModelProperty(value = "统一社会信用代码")
    private String socialCode;

    @ApiModelProperty(value = "法人")
    private String legalPerson;

    @ApiModelProperty(value = "法人电话")
    private String legalMobile;

    @ApiModelProperty(value = "地域")
    private String region;

    @ApiModelProperty(value = "类别")
    private String type;

    @ApiModelProperty(value = "面积")
    private String area;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "联系电话")
    private String contactsMobile;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "从业人数")
    private Integer employed;

    @ApiModelProperty(value = "状态，0-待受理，1-已受理，2-勘察中，3-已通过，4-已驳回")
    private Integer status;

    @ApiModelProperty(value = "首页推荐，1-是，0-否")
    private Integer indexStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Integer createTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Integer updateTime;

    @ApiModelProperty(value = "删除时间", hidden = true)
    private Integer deleteTime;
}
