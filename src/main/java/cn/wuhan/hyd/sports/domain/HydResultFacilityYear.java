package cn.wuhan.hyd.sports.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
/**
 * 功能说明：体育基础设施-健身点位年数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_facility_year")
public class HydResultFacilityYear implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "quantity")
    @ApiModelProperty(value = "健身点位数量")
    private String quantity;

    @Column(name = "yearInspectRate")
    @ApiModelProperty(value = "本年巡检率")
    private String yearInspectRate;

    @Column(name = "yearInspectYes")
    @ApiModelProperty(value = "本年已巡检")
    private String yearInspectYes;

    @Column(name = "yearInspectNo")
    @ApiModelProperty(value = "本年待巡检")
    private String yearInspectNo;

    @Column(name = "yearRepairRate")
    @ApiModelProperty(value = "本年维修完成率")
    private String yearRepairRate;

    @Column(name = "yearRepairYes")
    @ApiModelProperty(value = "本年已维修")
    private String yearRepairYes;

    @Column(name = "yearRepairNo")
    @ApiModelProperty(value = "本年待维修")
    private String yearRepairNo;

    @JsonIgnore
    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Column(name = "statisticalYear")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @JsonIgnore
    @Column(name = "updateTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

    @JsonIgnore
    @Column(name = "importTime")
    @ApiModelProperty(value = "导入时间", hidden = true)
    private Timestamp importTime;

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HydResultFacilityYear that = (HydResultFacilityYear) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
