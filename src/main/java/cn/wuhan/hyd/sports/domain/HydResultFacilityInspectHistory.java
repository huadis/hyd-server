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
 * 功能说明：结果表（历史）-体育基础设施-巡检动态 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_facility_inspect_history")
public class HydResultFacilityInspectHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "code")
    @ApiModelProperty(value = "设施点编号")
    private String code;

    @Column(name = "districtName")
    @ApiModelProperty(value = "所属区县")
    private String districtName;

    @Column(name = "streetName")
    @ApiModelProperty(value = "所属街道")
    private String streetName;

    @Column(name = "location")
    @ApiModelProperty(value = "安装点名称")
    private String location;

    @Column(name = "type")
    @ApiModelProperty(value = "巡检单类型")
    private String type;

    @Column(name = "inspector")
    @ApiModelProperty(value = "巡检人名称")
    private String inspector;

    @Column(name = "status")
    @ApiModelProperty(value = "巡检完成状态")
    private String status;

    @Column(name = "completeTime")
    @ApiModelProperty(value = "巡检完成时间")
    private String completeTime;

    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Column(name = "statisticalYear")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @Column(name = "updateTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

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
        HydResultFacilityInspectHistory that = (HydResultFacilityInspectHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
