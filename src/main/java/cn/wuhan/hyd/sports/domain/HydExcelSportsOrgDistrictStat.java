package cn.wuhan.hyd.sports.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 体育组织-区属统计实体类
 * 功能说明：存储体育组织按区属统计的数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_excel_sports_organization_district_stat")
public class HydExcelSportsOrgDistrictStat implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "districtName", length = 255)
    private String districtName; // 地区

    @Column(name = "districtNum")
    private Long districtNum; // 数量

    @Column(name = "batchNo", length = 50)
    private String batchNo; // 批次号

    @Column(name = "createdTime", nullable = false, updatable = false)
    private Date createdTime;

    @Column(name = "updateTime", nullable = false)
    private Date updateTime;

    @Column(name = "importTime", nullable = false)
    private Date importTime;

    // 保存前自动填充时间
    @PrePersist
    public void prePersist() {
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.importTime = new Timestamp(System.currentTimeMillis());
        this.updateTime = this.createdTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = new Timestamp(System.currentTimeMillis());
        this.importTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydExcelSportsOrgDistrictStat that = (HydExcelSportsOrgDistrictStat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
