package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：组织机构表实体类 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
@Data
public class HydOriginTenantReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "名称")
    private String tenantName;

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

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "法人代表")
    private String legalPerson;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "营业执照")
    private String businessLicenseImg;

    @ApiModelProperty(value = "公司照片")
    private String tenantIcon;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
