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
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "key", length = 100)
    @NotNull(message = "键不能为空")
    @ApiModelProperty(value = "键", required = true)
    private String key;

    @Column(name = "value", nullable = false)
    @NotNull(message = "值不能为空")
    @ApiModelProperty(value = "值", required = true)
    private String value;

    @Column(name = "remark", length = 500)
    @ApiModelProperty(value = "备注说明")
    private String remark;

    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @JsonIgnore
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
