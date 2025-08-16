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
 * 功能说明：订单表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_order")
public class HydOriginOrder implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = false, required = true)
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

    @Column(name = "courseId")
    @ApiModelProperty(value = "课程id")
    private String courseId;

    @Column(name = "orderAmount")
    @ApiModelProperty(value = "订单金额，单位分")
    private Integer orderAmount;  // 表中为int类型，对应Integer

    @Column(name = "actualAmount")
    @ApiModelProperty(value = "实付金额，单位分")
    private Integer actualAmount;

    @Column(name = "couponAmount")
    @ApiModelProperty(value = "消费券金额，单位分")
    private Integer couponAmount;

    @Column(name = "refundAmount")
    @ApiModelProperty(value = "退款金额，单位分")
    private Integer refundAmount;

    @Column(name = "useCoupon")
    @ApiModelProperty(value = "是否使用消费券")
    private Integer useCoupon;  // 表中为tinyint类型，对应Integer

    @Column(name = "userAge")
    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;

    @Column(name = "userGender")
    @ApiModelProperty(value = "用户性别")
    private String userGender;

    @Column(name = "studentAge")
    @ApiModelProperty(value = "学员年龄")
    private Integer studentAge;

    @Column(name = "studentGender")
    @ApiModelProperty(value = "学员性别")
    private String studentGender;

    @Column(name = "studentRegion")
    @ApiModelProperty(value = "学员所属区域")
    private String studentRegion;

    @Column(name = "orderStatus")
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydOriginOrder that = (HydOriginOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
