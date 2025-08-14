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
 * 功能说明：培训活动支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_training_activity_item")
public class HydTrainingActivityItem implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = true)
    private String id;  // 表中主键为VARCHAR类型，此处用String

    @Column(name = "activityId")
    @ApiModelProperty(value = "活动id")
    private String activityId;

    @Column(name = "sportCode")
    @ApiModelProperty(value = "项目类型编码")
    private String sportCode;

    @Column(name = "sportName")
    @ApiModelProperty(value = "项目类型名称")
    private String sportName;

    @Column(name = "ageStartLimit")
    @ApiModelProperty(value = "培训对象年龄起始限制")
    private String ageStartLimit;

    @Column(name = "ageEndLimit")
    @ApiModelProperty(value = "培训对象年龄结束限制")
    private String ageEndLimit;

    @Column(name = "disabled")
    @ApiModelProperty(value = "状态（0-启用，1-停用 ）")
    private Integer disabled;  // 表中TINYINT类型，用Integer接收

    @Column(name = "createdTime")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @Column(name = "updatedTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;

    // 保存前自动填充时间
    @PrePersist
    public void prePersist() {
        this.createdTime = new Timestamp(System.currentTimeMillis());
        this.updatedTime = this.createdTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedTime = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydTrainingActivityItem that = (HydTrainingActivityItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
