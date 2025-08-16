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
 * 功能说明：校外培训机构附件表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Entity
@Getter
@Setter
@Table(name = "hyd_origin_la_stadium_file_history")
public class HydOriginLaStadiumFileHistory implements Serializable {

    @Id
    @Column(name = "id")
    @NotNull
    @ApiModelProperty(value = "主键ID", hidden = false, required = true)
    private Integer id;

    @Column(name = "userId")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @Column(name = "stadiumId")
    @ApiModelProperty(value = "场馆id")
    private Integer stadiumId;

    @Column(name = "mczzsbgzs")
    @ApiModelProperty(value = "《名称自主申报告知书》或《民办非企业单位成立名称核准表》")
    private String mczzsbgzs;

    @Column(name = "xwpxjgsqdjb")
    @ApiModelProperty(value = "湖北省体育类校外培训机构申请登记表")
    private String xwpxjgsqdjb;

    @Column(name = "pxjgzc")
    @ApiModelProperty(value = "消防安全承诺书")
    private String pxjgzc;

    @Column(name = "zjtrdyxzm")
    @ApiModelProperty(value = "培训机构章程")
    private String zjtrdyxzm;

    @Column(name = "pxjgrymx")
    @ApiModelProperty(value = "资金投入的有效证明材料")
    private String pxjgrymx;

    @Column(name = "wfczns")
    @ApiModelProperty(value = "湖北省体育类类校外培训机构从业人员明细表")
    private String wfczns;

    @Column(name = "fwcqzm")
    @ApiModelProperty(value = "从业人员无犯罪记录、无失德失信或严重违纪行为承诺书")
    private String fwcqzm;

    @Column(name = "pxcsnbjgt")
    @ApiModelProperty(value = "培训场所房屋产权证明、租赁(借用)合同")
    private String pxcsnbjgt;

    @Column(name = "pxcsnbpmt")
    @ApiModelProperty(value = "培训场所内部结构平面图")
    private String pxcsnbpmt;

    @Column(name = "nbglzd")
    @ApiModelProperty(value = "内部管理制度")
    private String nbglzd;

    @Column(name = "yljjpjsb")
    @ApiModelProperty(value = "配备常规、医疗急救药品及设备")
    private String yljjpjsb;

    @Column(name = "pxjgjcbab")
    @ApiModelProperty(value = "《湖北省类培训机构教材备案表》以及培训计划、教学大纲、培训教材和其他培训材料")
    private String pxjgjcbab;

    @Column(name = "lhjbtylpxjgxy")
    @ApiModelProperty(value = "联合举办体育类培训机构的，应提交联合办学协议")
    private String lhjbtylpxjgxy;

    @Column(name = "bxht")
    @ApiModelProperty(value = "保险合同")
    private String bxht;

    @Column(name = "remark")
    @ApiModelProperty(value = "其他")
    private String remark;

    @Column(name = "createTime")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Integer createTime;  // 表中为INT类型（时间戳），对应Integer

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HydOriginLaStadiumFileHistory that = (HydOriginLaStadiumFileHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
