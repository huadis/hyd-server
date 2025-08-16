package cn.wuhan.hyd.sports.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 功能说明：组织机构表实体类 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月14日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_tenant")
public class HydOriginTenant implements Serializable {

    @Id
    @Column(name = "id", length = 32)
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = false, required = true)
    private String id;

    @Column(name = "tenantName", length = 256)
    @ApiModelProperty(value = "名称")
    private String tenantName;

    @Column(name = "shortName", length = 128)
    @ApiModelProperty(value = "简称")
    private String shortName;

    @Column(name = "province", length = 32)
    @ApiModelProperty(value = "省编码")
    private String province;

    @Column(name = "city", length = 32)
    @ApiModelProperty(value = "市编码")
    private String city;

    @Column(name = "district", length = 32)
    @ApiModelProperty(value = "区编码")
    private String district;

    @Column(name = "provinceName", length = 33)
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @Column(name = "cityName", length = 34)
    @ApiModelProperty(value = "市名称")
    private String cityName;

    @Column(name = "districtName", length = 35)
    @ApiModelProperty(value = "区名称")
    private String districtName;

    @Column(name = "address", length = 256)
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "legalPerson", length = 128)
    @ApiModelProperty(value = "法人代表")
    private String legalPerson;

    @Column(name = "phone", length = 32)
    @ApiModelProperty(value = "电话")
    private String phone;

    @Column(name = "businessLicenseImg", length = 256)
    @ApiModelProperty(value = "营业执照")
    private String businessLicenseImg;

    @Column(name = "tenantIcon", length = 256)
    @ApiModelProperty(value = "公司照片")
    private String tenantIcon;

    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @Column(name = "updatedTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;

    @Column(name = "importTime")
    @ApiModelProperty(value = "导入时间", hidden = true)
    private Timestamp importTime;

    // 保存前自动填充时间
    @PrePersist
    public void prePersist() {
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.importTime = new Timestamp(System.currentTimeMillis());
        this.updatedTime = this.createdTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = new Timestamp(System.currentTimeMillis());
        this.importTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydOriginTenant hydOriginTenant = (HydOriginTenant) o;
        return Objects.equals(id, hydOriginTenant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
