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
 * 功能说明：结果表-体育消费卷-券用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_coupon_user_age")
public class HydResultCouponUserAge implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "under25Num")
    @ApiModelProperty(value = "25岁以下")
    private String under25Num;

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

    @Column(name = "bt51and60Num")
    @ApiModelProperty(value = "51-60岁")
    private String bt51and60Num;

    @Column(name = "over60Num")
    @ApiModelProperty(value = "60岁以上")
    private String over60Num;

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
        HydResultCouponUserAge that = (HydResultCouponUserAge) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}





