package cn.wuhan.hyd.sports.domain;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 功能说明：青少年技能培训 - 课程热度排行TOP5 <br>
 * 开发人员：@author haudi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_order_ykt_course_stat")
public class HydResultOrderYktCourseStat implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "course")
    @ExcelField(name = "课程名称")
    @ApiModelProperty(value = "课程名称，如青少年篮球基础课")
    private String course;

    @Column(name = "num")
    @ExcelField(name = "课程热度数量")
    @ApiModelProperty(value = "课程热度统计数量（如报名人数、订单数）")
    private Long num;

    @Column(name = "batchNo")
    @ExcelField(name = "批次号")
    @ApiModelProperty(value = "批次号，用于标识数据批次")
    private String batchNo;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydResultOrderYktCourseStat that = (HydResultOrderYktCourseStat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
