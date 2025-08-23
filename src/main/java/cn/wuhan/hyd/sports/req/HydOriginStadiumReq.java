package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：培训场馆表实体类 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginStadiumReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @ApiModelProperty(value = "名称")
    private String stadiumName;

    @ApiModelProperty(value = "简称")
    private String shortName;

    @ApiModelProperty(value = "省编码")
    private String province;

    @ApiModelProperty(value = "市编码")
    private String city;

    @ApiModelProperty(value = "区编码")
    private String district;

    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @ApiModelProperty(value = "市名称")
    private String cityName;

    @ApiModelProperty(value = "区名称")
    private String districtName;

    @ApiModelProperty(value = "街道")
    private String street;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "面积")
    private String area;

    @ApiModelProperty(value = "可容纳人数")
    private Integer holdNum;

    @ApiModelProperty(value = "联系人")
    private String contactName;

    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @ApiModelProperty(value = "是否上线（0-未上线，1-上线）")
    private Integer online;

    @ApiModelProperty(value = "场馆性质（0-社会的，1-公共的）")
    private Integer investmentNature;

    @ApiModelProperty(value = "状态（0-启用，1-停用）")
    private Integer disabled;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
