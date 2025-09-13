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
 * 功能说明：大众赛事-总览信息表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月26日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_events_overview_stat")
public class HydResultEventsOverviewStat implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "statisticalYear")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @Column(name = "total")
    @ApiModelProperty(value = "总赛事场次")
    private Long total;

    @Column(name = "participantCount")
    @ApiModelProperty(value = "总参与人数")
    private Long participantCount;

    @Column(name = "internationalCount")
    @ApiModelProperty(value = "国际赛事")
    private Long internationalCount;

    @Column(name = "nationalCount")
    @ApiModelProperty(value = "国家级赛事")
    private Long nationalCount;

    @Column(name = "provinceCount")
    @ApiModelProperty(value = "省级赛事")
    private Long provinceCount;

    @Column(name = "cityCount")
    @ApiModelProperty(value = "市级赛事")
    private Long cityCount;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydResultEventsOverviewStat that = (HydResultEventsOverviewStat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
