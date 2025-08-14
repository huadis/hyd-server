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
 * 功能说明：培训课程表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_training_course")
public class HydTrainingCourse implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = true)
    private String id;  // 表主键为varchar类型，对应String

    @Column(name = "activityId")
    @ApiModelProperty(value = "活动id")
    private String activityId;

    @Column(name = "tenantId")
    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @Column(name = "stadiumId")
    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @Column(name = "stadiumItemId")
    @ApiModelProperty(value = "项目id")
    private String stadiumItemId;

    @Column(name = "courseName")
    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @Column(name = "startTime")
    @ApiModelProperty(value = "开课时间")
    private Long startTime;  // 表中为bigint类型，对应Long

    @Column(name = "endTime")
    @ApiModelProperty(value = "结课时间")
    private Long endTime;

    @Column(name = "registrationStartTime")
    @ApiModelProperty(value = "报名开始时间")
    private Long registrationStartTime;

    @Column(name = "registrationEndTime")
    @ApiModelProperty(value = "报名结束时间")
    private Long registrationEndTime;

    @Column(name = "headCountLimit")
    @ApiModelProperty(value = "限报人数限制")
    private Integer headCountLimit;  // 表中为int类型，对应Integer

    @Column(name = "durationLimit")
    @ApiModelProperty(value = "课程时长，单位分钟")
    private Integer durationLimit;

    @Column(name = "totalCountLimit")
    @ApiModelProperty(value = "限报人数限制")
    private Integer totalCountLimit;

    @Column(name = "ageStartLimit")
    @ApiModelProperty(value = "培训对象年龄起始")
    private String ageStartLimit;

    @Column(name = "ageEndLimit")
    @ApiModelProperty(value = "培训对象年龄结束")
    private String ageEndLimit;

    @Column(name = "price")
    @ApiModelProperty(value = "价格，单位分")
    private Integer price;

    @Column(name = "hydOnline")
    @ApiModelProperty(value = "小程序上下线")
    private Integer hydOnline;  // 表中为tinyint类型，对应Integer

    @Column(name = "disabled")
    @ApiModelProperty(value = "状态，0-启用，1-停用")
    private Integer disabled;

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
        HydTrainingCourse that = (HydTrainingCourse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
