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
 * 功能说明：校外培训机构
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_la_stadium")
public class HydOriginLaStadium implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键", hidden = false, required = true)
    private Integer id;

    @Column(name = "userId")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @Column(name = "title")
    @ApiModelProperty(value = "名称")
    private String title;

    @Column(name = "certBus")
    @ApiModelProperty(value = "营业类型")
    private String certBus;

    @Column(name = "doorImages")
    @ApiModelProperty(value = "门头照片")
    private String doorImages;

    @Column(name = "busHours")
    @ApiModelProperty(value = "营业时间")
    private String busHours;

    @Column(name = "socialCode")
    @ApiModelProperty(value = "统一社会信用代码")
    private String socialCode;

    @Column(name = "legalPerson")
    @ApiModelProperty(value = "法人")
    private String legalPerson;

    @Column(name = "legalMobile")
    @ApiModelProperty(value = "法人电话")
    private String legalMobile;

    @Column(name = "region")
    @ApiModelProperty(value = "地域")
    private String region;

    @Column(name = "type")
    @ApiModelProperty(value = "类别")
    private String type;

    @Column(name = "area")
    @ApiModelProperty(value = "面积")
    private String area;

    @Column(name = "contacts")
    @ApiModelProperty(value = "联系人")
    private String contacts;

    @Column(name = "contactsMobile")
    @ApiModelProperty(value = "联系电话")
    private String contactsMobile;

    @Column(name = "address")
    @ApiModelProperty(value = "地址")
    private String address;

    @Column(name = "employed")
    @ApiModelProperty(value = "从业人数")
    private Integer employed;

    @Column(name = "status")
    @ApiModelProperty(value = "状态，0-待受理，1-已受理，2-勘察中，3-已通过，4-已驳回")
    private Integer status;

    @Column(name = "indexStatus")
    @ApiModelProperty(value = "首页推荐，1-是，0-否")
    private Integer indexStatus;

    @Column(name = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @Column(name = "createTime", updatable = false)
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Integer createTime;

    @Column(name = "updateTime")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Integer updateTime;

    @Column(name = "deleteTime")
    @ApiModelProperty(value = "删除时间", hidden = true)
    private Integer deleteTime;

    @Column(name = "importTime")
    @ApiModelProperty(value = "导入时间", hidden = true)
    private Timestamp importTime;

    // 保存前自动填充时间
    @PrePersist
    public void prePersist() {
        this.importTime = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void preUpdate() {
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
        HydOriginLaStadium that = (HydOriginLaStadium) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
