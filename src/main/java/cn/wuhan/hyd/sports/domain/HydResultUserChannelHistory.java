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
 * 功能说明：结果表（历史）-场馆预定-用户来源渠道 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_user_channel_history")
public class HydResultUserChannelHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "registerUserNum")
    @ApiModelProperty(value = "注册用户")
    private String registerUserNum;

    @Column(name = "realNameUserNum")
    @ApiModelProperty(value = "实名用户")
    private String realNameUserNum;

    @Column(name = "receiveCouponUserNum")
    @ApiModelProperty(value = "领券用户")
    private String receiveCouponUserNum;

    @Column(name = "useCouponUserNum")
    @ApiModelProperty(value = "用券用户")
    private String useCouponUserNum;

    @Column(name = "orderUserNum")
    @ApiModelProperty(value = "下单用户")
    private String orderUserNum;

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
        HydResultUserChannelHistory that = (HydResultUserChannelHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
