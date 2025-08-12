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
 * 功能说明：体育消费卷-券用户分析 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_coupon_user")
public class HydCouponUser implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "receiveCouponNum")
    @ApiModelProperty(value = "领券人数")
    private String receiveCouponNum;

    @Column(name = "useCouponNum")
    @ApiModelProperty(value = "用券人数")
    private String useCouponNum;

    @Column(name = "maleNum")
    @ApiModelProperty(value = "男性人数")
    private String maleNum;

    @Column(name = "femaleNum")
    @ApiModelProperty(value = "女性人数")
    private String femaleNum;

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
        HydCouponUser that = (HydCouponUser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
