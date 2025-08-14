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
 * 功能说明：体育消费卷-消费券总金额 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_coupon_amount")
public class HydCouponAmount implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "sendAmount")
    @ApiModelProperty(value = "发放金额")
    private String sendAmount;

    @Column(name = "receiveCount")
    @ApiModelProperty(value = "领券人次")
    private String receiveCount;

    @Column(name = "usedCount")
    @ApiModelProperty(value = "用券人次")
    private String usedCount;

    @Column(name = "usedRatio")
    @ApiModelProperty(value = "用券资金占比")
    private String usedRatio;

    @Column(name = "useCouponAmount")
    @ApiModelProperty(value = "用券金额")
    private String useCouponAmount;

    @Column(name = "orderAmount")
    @ApiModelProperty(value = "订单金额")
    private String orderAmount;

    @Column(name = "orderRatio")
    @ApiModelProperty(value = "拉动消费比")
    private String orderRatio;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydCouponAmount that = (HydCouponAmount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
