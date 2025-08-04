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
 * 功能说明：场馆预定-在线场馆数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_stadium")
public class HydStadium implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "onlineStadiumNum")
    @ApiModelProperty(value = "在线场馆数量")
    private String onlineStadiumNum;

    @Column(name = "couponStadiumNum")
    @ApiModelProperty(value = "消费券场馆数量")
    private String couponStadiumNum;

    @Column(name = "socialStadiumNum")
    @ApiModelProperty(value = "社会场馆数量")
    private String socialStadiumNum;

    @Column(name = "publicStadiumNum")
    @ApiModelProperty(value = "公共场馆数量")
    private String publicStadiumNum;

    @Column(name = "useCouponStadiumNum")
    @ApiModelProperty(value = "累计用券场馆数量")
    private String useCouponStadiumNum;

    @Column(name = "createdTime")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @Column(name = "updateTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HydStadium that = (HydStadium) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
