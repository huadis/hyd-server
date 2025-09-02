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
 * 功能说明：大众赛事-体育赛事信息表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_excel_public_events_history")
public class HydExcelPublicEventsHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "sequence")
    @ExcelField(name = "序号")
    @ApiModelProperty(value = "序号")
    private Long sequence;

    @Column(name = "district")
    @ExcelField(name = "区属")
    @ApiModelProperty(value = "区属，如江夏区")
    private String district;

    @Column(name = "eventName")
    @ExcelField(name = "赛事名称")
    @ApiModelProperty(value = "赛事名称")
    private String eventName;

    @Column(name = "sportItem")
    @ExcelField(name = "运动项目")
    @ApiModelProperty(value = "运动项目")
    private String sportItem;

    @Column(name = "hostUnit")
    @ExcelField(name = "主办单位")
    @ApiModelProperty(value = "主办单位")
    private String hostUnit;

    @Column(name = "organizerUnit")
    @ExcelField(name = "承办单位")
    @ApiModelProperty(value = "承办单位")
    private String organizerUnit;

    @Column(name = "eventYear")
    @ExcelField(name = "赛事年份")
    @ApiModelProperty(value = "赛事年份")
    private String eventYear;

    @Column(name = "eventMonth")
    @ExcelField(name = "赛事月份")
    @ApiModelProperty(value = "赛事月份")
    private String eventMonth;

    @Column(name = "eventDate")
    @ExcelField(name = "赛事日期")
    @ApiModelProperty(value = "赛事日期")
    private String eventDate;

    @Column(name = "eventLocation")
    @ExcelField(name = "赛事活动地点")
    @ApiModelProperty(value = "赛事活动地点")
    private String eventLocation;

    @Column(name = "participantCount")
    @ExcelField(name = "参赛规模（人数）")
    @ApiModelProperty(value = "参赛规模（人数）")
    private Integer participantCount;

    @Column(name = "eventLevel")
    @ExcelField(name = "赛事级别（国际级、国家级、省级、市级、区级、街道级、社区级）")
    @ApiModelProperty(value = "赛事级别（国际级、国家级、省级、市级、区级、街道级、社区级）")
    private String eventLevel;

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
        HydExcelPublicEventsHistory that = (HydExcelPublicEventsHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
