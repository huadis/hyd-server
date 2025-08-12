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
 * 功能说明：体育产业-从业人员数量（分类统计）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_industry_employee_count")
public class HydIndustryEmployeeCount implements Serializable {

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

    @Column(name = "entityType")
    @ExcelField(name = "从业类型（体育管理活动 / 竞赛表演等）")
    @ApiModelProperty(value = "从业类型（体育管理活动 / 竞赛表演等）")
    private String entityType;

    @Column(name = "personCount")
    @ExcelField(name = "人数（人）")
    @ApiModelProperty(value = "人数（人）")
    private Integer personCount;

    @Column(name = "dataSource")
    @ExcelField(name = "数据来源 / 备注")
    @ApiModelProperty(value = "数据来源/备注")
    private String dataSource;

    @Column(name = "createdTime")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @Column(name = "updateTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

    // 保存前自动填充时间
    @PrePersist
    public void prePersist() {
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.updateTime = this.createdTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydIndustryEmployeeCount that = (HydIndustryEmployeeCount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

