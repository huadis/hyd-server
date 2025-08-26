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
 * 功能说明：大众赛事-参赛人数人档表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_events_participant_level")
public class HydResultEventsParticipantLevel implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "statisticalYear")
    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @Column(name = "participantLevel")
    @ExcelField(name = "人数人档级别")
    @ApiModelProperty(value = "人数人档级别")
    private String participantLevel;

    @Column(name = "count")
    @ExcelField(name = "数量")
    @ApiModelProperty(value = "数量")
    private Long count;

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
        HydResultEventsParticipantLevel that = (HydResultEventsParticipantLevel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
