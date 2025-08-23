package cn.wuhan.hyd.sports.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：培训场馆表实体类 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_stadium_history")
public class HydOriginStadiumHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = false, required = true)
    private String id;

    @Column(name = "tenantId")
    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @Column(name = "stadiumName")
    @ApiModelProperty(value = "名称")
    private String stadiumName;

    @Column(name = "shortName")
    @ApiModelProperty(value = "简称")
    private String shortName;

    @Column(name = "province")
    @ApiModelProperty(value = "省编码")
    private String province;

    @Column(name = "city")
    @ApiModelProperty(value = "市编码")
    private String city;

    @Column(name = "district")
    @ApiModelProperty(value = "区编码")
    private String district;

    @Column(name = "provinceName")
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    @Column(name = "cityName")
    @ApiModelProperty(value = "市名称")
    private String cityName;

    @Column(name = "districtName")
    @ApiModelProperty(value = "区名称")
    private String districtName;

    @Column(name = "street")
    @ApiModelProperty(value = "街道")
    private String street;

    @Column(name = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "longitude", nullable = false, length = 11)
    @ApiModelProperty(value = "经度")
    private String longitude;

    // 纬度：字符串类型，同理添加校验
    @Column(name = "latitude", nullable = false, length = 11)
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @Column(name = "area")
    @ApiModelProperty(value = "面积")
    private String area;

    @Column(name = "holdNum")
    @ApiModelProperty(value = "可容纳人数")
    private Integer holdNum;

    @Column(name = "contactName")
    @ApiModelProperty(value = "联系人")
    private String contactName;

    @Column(name = "telephone")
    @ApiModelProperty(value = "联系电话")
    private String telephone;

    @Column(name = "online")
    @ApiModelProperty(value = "是否上线（0-未上线，1-上线）")
    private Integer online;

    @Column(name = "investmentNature")
    @ApiModelProperty(value = "场馆性质（0-社会的，1-公共的）")
    private Integer investmentNature;

    @Column(name = "disabled")
    @ApiModelProperty(value = "状态（0-启用，1-停用）")
    private Integer disabled;

    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @CreatedDate
    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @LastModifiedDate
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
        HydOriginStadiumHistory that = (HydOriginStadiumHistory) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
