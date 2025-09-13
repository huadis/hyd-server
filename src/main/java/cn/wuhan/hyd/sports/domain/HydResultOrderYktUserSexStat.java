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
 * 功能说明：青少年技能培训 - 性别统计 <br>
 * 开发人员：@author haudi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_order_ykt_usersex_stat")
public class HydResultOrderYktUserSexStat implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "gender")
    @ApiModelProperty(value = "性别，如男、女")
    private String gender;

    @Column(name = "num")
    @ApiModelProperty(value = "对应性别的统计数量")
    private Long num;

    @JsonIgnore
    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号，用于标识数据批次")
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydResultOrderYktUserSexStat that = (HydResultOrderYktUserSexStat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
