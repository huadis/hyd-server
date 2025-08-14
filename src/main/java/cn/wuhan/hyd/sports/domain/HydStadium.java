package cn.wuhan.hyd.sports.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 功能说明：培训场馆表实体类 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Table(name = "hyd_stadium")
@Getter
@Setter
public class HydStadium implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键id")
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

    @Column(name = "location")
    @ApiModelProperty(value = "经纬度")
    private String location;

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

    @CreatedDate
    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createdTime;

    @LastModifiedDate
    @Column(name = "updatedTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date updatedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydStadium that = (HydStadium) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
