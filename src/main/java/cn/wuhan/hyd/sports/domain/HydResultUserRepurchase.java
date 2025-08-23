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
 * 功能说明：结果表-场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_result_user_repurchase")
public class HydResultUserRepurchase implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键ID", hidden = true)
    private Long id;

    @Column(name = "1Num")
    @ApiModelProperty(value = "一次下单")
    private String onceNum;  // 变量名调整为符合Java命名规范，通过@Column映射到数据库字段"1Num"

    @Column(name = "bt2and5Num")
    @ApiModelProperty(value = "2-5次")
    private String bt2and5Num;

    @Column(name = "over5Num")
    @ApiModelProperty(value = "5次以上")
    private String over5Num;

    @Column(name = "over10Num")
    @ApiModelProperty(value = "10次以上")
    private String over10Num;

    @Column(name = "over50Num")
    @ApiModelProperty(value = "50次以上")
    private String over50Num;

    @Column(name = "batchNo")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HydResultUserRepurchase that = (HydResultUserRepurchase) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
