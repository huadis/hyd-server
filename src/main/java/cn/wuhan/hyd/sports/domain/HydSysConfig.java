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
 * 功能说明：系统模块-配置表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年09月11日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_sys_config")
public class HydSysConfig implements Serializable {

    @Id
    @Column(name = "moduleName", length = 100)
    @NotNull(message = "模块名称不能为空")
    @ApiModelProperty(value = "模块名称（唯一标识）", required = true)
    private String moduleName;

    @Column(name = "autoRefresh", nullable = false)
    @ApiModelProperty(value = "是否自动更新：1-是，0-否", allowableValues = "0,1", required = true)
    private Boolean autoRefresh;

    @Column(name = "remark", length = 500)
    @ApiModelProperty(value = "备注说明")
    private String remark;

    @Column(name = "createdTime", updatable = false)
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
        HydSysConfig that = (HydSysConfig) o;
        return Objects.equals(moduleName, that.moduleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleName);
    }
}
