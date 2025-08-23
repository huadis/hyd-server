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
 * 功能说明：培训活动表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_training_activity")
public class HydOriginTrainingActivity implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = false, required = true)
    private String id;  // 注意这里主键类型是String，和表结构一致，不同于模板里的Long

    @Column(name = "activityName")
    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @Column(name = "organizingCompany")
    @ApiModelProperty(value = "主办单位")
    private String organizingCompany;

    @Column(name = "startTime")
    @ApiModelProperty(value = "活动开始时间")
    private Timestamp startTime;  // 表结构是DATETIME，对应java.sql.Timestamp

    @Column(name = "endTime")
    @ApiModelProperty(value = "活动结束时间")
    private Timestamp endTime;

    @Column(name = "open")
    @ApiModelProperty(value = "启用开关（0-关闭，1-打开 ）")
    private Integer open;  // 表结构TINYINT，用Integer接收

    @Column(name = "disabled")
    @ApiModelProperty(value = "状态（0-启用，1-停用 ）")
    private Integer disabled;

    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

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
        HydOriginTrainingActivity that = (HydOriginTrainingActivity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
