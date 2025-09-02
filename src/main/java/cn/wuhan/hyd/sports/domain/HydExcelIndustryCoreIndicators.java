package cn.wuhan.hyd.sports.domain;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 功能说明：体育产业-核心指标总表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_excel_industry_core_indicators")
public class HydExcelIndustryCoreIndicators implements Serializable {

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

    @Column(name = "totalOutputValue")
    @ExcelField(name = "体育产业总产值（亿）")
    @ApiModelProperty(value = "体育产业总产值（亿）")
    private BigDecimal totalOutputValue;

    @Column(name = "addedValue")
    @ExcelField(name = "产业增加值（亿）")
    @ApiModelProperty(value = "产业增加值（亿）")
    private BigDecimal addedValue;

    @Column(name = "marketEntityCount")
    @ExcelField(name = "体育市场主体总量（家）")
    @ApiModelProperty(value = "体育市场主体总量（家）")
    private Integer marketEntityCount;

    @Column(name = "employeeCount")
    @ExcelField(name = "从业人员数量（万）")
    @ApiModelProperty(value = "从业人员数量（万）")
    private BigDecimal employeeCount;

    @Column(name = "sportsConsumptionTotalScale")
    @ExcelField(name = "体育消费总规模（万）")
    @ApiModelProperty(value = "体育消费总规模（万）")
    private BigDecimal sportsConsumptionTotalScale;

    @Column(name = "perCapitaSportsConsumption")
    @ExcelField(name = "人均体育消费（元）")
    @ApiModelProperty(value = "人均体育消费（元）")
    private BigDecimal perCapitaSportsConsumption;

    @Column(name = "otherSportsServicesRevenueGrowthRate")
    @ExcelField(name = "体育其他服务业营收增速")
    @ApiModelProperty(value = "体育其他服务业营收增速")
    private BigDecimal otherSportsServicesRevenueGrowthRate;

    @Column(name = "remark")
    @ExcelField(name = "备注")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号")
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
        HydExcelIndustryCoreIndicators that = (HydExcelIndustryCoreIndicators) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
