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
 * 功能说明：场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_stadium_item_history")
public class HydOriginStadiumItemHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = false, required = true)
    private String id;

    @Column(name = "tenantId")
    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @Column(name = "stadiumId")
    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @Column(name = "stadiumItemName")
    @ApiModelProperty(value = "项目名称")
    private String stadiumItemName;

    @Column(name = "sportCode")
    @ApiModelProperty(value = "项目类型编码")
    private String sportCode;

    @Column(name = "sportName")
    @ApiModelProperty(value = "项目类型名称")
    private String sportName;

    @Column(name = "disabled")
    @ApiModelProperty(value = "状态（0-启用，1-停用 ）")
    private Integer disabled;

    @Column(name = "createdTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @Column(name = "updatedTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;

    @Column(name = "importTime")
    @ApiModelProperty(value = "导入时间", hidden = true)
    private Timestamp importTime;

    // 保存前自动填充时间
    @PrePersist
    public void prePersist() {
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.importTime = new Timestamp(System.currentTimeMillis());
        this.updatedTime = this.createdTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = new Timestamp(System.currentTimeMillis());
        this.importTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HydOriginStadiumItemHistory that = (HydOriginStadiumItemHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
