package cn.wuhan.hyd.sports.domain;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 体育组织实体类
 * 功能说明：对应数据库表hyd_excel_sports_organization <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_excel_sports_organization")
public class HydExcelSportsOrg implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "sequence", nullable = false)
    @ExcelField(name = "序号")
    @ApiModelProperty(value = "序号")
    private Long sequence;

    @Column(name = "orgName")
    @ExcelField(name = "组织名称")
    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @Column(name = "address")
    @ExcelField(name = "地址")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "districtName")
    @ExcelField(name = "区属")
    @ApiModelProperty(value = "区属")
    private String districtName;

    @JsonIgnore
    @Column(name = "batchNo")
    private String batchNo;

    @Column(name = "createdTime", nullable = false, updatable = false)
    private Date createdTime;

    @JsonIgnore
    @Column(name = "updateTime", nullable = false)
    private Date updateTime;

    @JsonIgnore
    @Column(name = "importTime", nullable = false)
    private Date importTime;

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
        HydExcelSportsOrg that = (HydExcelSportsOrg) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
