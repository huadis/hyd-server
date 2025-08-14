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
 * 功能说明：结果表（历史）-场馆预定-年龄占比 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_user_age_history")
public class HydResultUserAgeHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "under18Num")
    @ApiModelProperty(value = "18岁以下")
    private String under18Num;

    @Column(name = "bt18and25Num")
    @ApiModelProperty(value = "18-25岁")
    private String bt18and25Num;

    @Column(name = "bt26and30Num")
    @ApiModelProperty(value = "26-30岁")
    private String bt26and30Num;

    @Column(name = "bt31and35Num")
    @ApiModelProperty(value = "31-35岁")
    private String bt31and35Num;

    @Column(name = "bt36and40Num")
    @ApiModelProperty(value = "36-40岁")
    private String bt36and40Num;

    @Column(name = "bt41and45Num")
    @ApiModelProperty(value = "41-45岁")
    private String bt41and45Num;

    @Column(name = "bt46and50Num")
    @ApiModelProperty(value = "46-50岁")
    private String bt46and50Num;

    @Column(name = "over50Num")
    @ApiModelProperty(value = "50岁以上")
    private String over50Num;

    @Column(name = "activityId")
    @ApiModelProperty(value = "活动id")
    private String activityId;

    @Column(name = "activityName")
    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @Column(name = "groupId")
    @ApiModelProperty(value = "分组id")
    private String groupId;

    @Column(name = "groupName")
    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @Column(name = "type")
    @ApiModelProperty(value = "类型")
    private String type;

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HydResultUserAgeHistory that = (HydResultUserAgeHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
