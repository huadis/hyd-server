package cn.wuhan.hyd.sports.domain;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * 功能说明：社会体育指导员汇总信息 <br>
 * 开发人员：@author haudi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_excel_instructor_info")
public class HydExcelInstructorInfo implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "name")
    @ExcelField(name = "姓名")
    @ApiModelProperty(value = "姓名")
    private String name;

    @Column(name = "gender")
    @ExcelField(name = "性别（男/女）")
    @ApiModelProperty(value = "性别（男/女）")
    private String gender;

    @Column(name = "birthDate")
    @ExcelField(name = "出生日期")
    @ApiModelProperty(value = "出生日期")
    private String birthDate;

    @Column(name = "serviceProject")
    @ExcelField(name = "服务项目")
    @ApiModelProperty(value = "服务项目")
    private String serviceProject;

    @Column(name = "level")
    @ExcelField(name = "级别")
    @ApiModelProperty(value = "级别")
    private String level;

    @Column(name = "certifyTime")
    @ExcelField(name = "获证时间")
    @ApiModelProperty(value = "获证时间")
    private String certificateDate;

    @Column(name = "region")
    @ExcelField(name = "所在地区")
    @ApiModelProperty(value = "所在地区")
    private String region;

    @Column(name = "remark")
    @ExcelField(name = "备注")
    @ApiModelProperty(value = "备注")
    private String remark;

    @Column(name = "uploadTime")
    @ExcelField(name = "上传时间")
    @ApiModelProperty(value = "上传时间")
    private String uploadTime;

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
        HydExcelInstructorInfo that = (HydExcelInstructorInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
