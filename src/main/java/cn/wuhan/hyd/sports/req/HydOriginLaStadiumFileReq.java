package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：校外培训机构附件表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginLaStadiumFileReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "场馆id")
    private Integer stadiumId;

    @ApiModelProperty(value = "《名称自主申报告知书》或《民办非企业单位成立名称核准表》")
    private String mczzsbgzs;

    @ApiModelProperty(value = "湖北省体育类校外培训机构申请登记表")
    private String xwpxjgsqdjb;

    @ApiModelProperty(value = "消防安全承诺书")
    private String pxjgzc;

    @ApiModelProperty(value = "培训机构章程")
    private String zjtrdyxzm;

    @ApiModelProperty(value = "资金投入的有效证明材料")
    private String pxjgrymx;

    @ApiModelProperty(value = "湖北省体育类类校外培训机构从业人员明细表")
    private String wfczns;

    @ApiModelProperty(value = "从业人员无犯罪记录、无失德失信或严重违纪行为承诺书")
    private String fwcqzm;

    @ApiModelProperty(value = "培训场所房屋产权证明、租赁(借用)合同")
    private String pxcsnbjgt;

    @ApiModelProperty(value = "培训场所内部结构平面图")
    private String pxcsnbpmt;

    @ApiModelProperty(value = "内部管理制度")
    private String nbglzd;

    @ApiModelProperty(value = "配备常规、医疗急救药品及设备")
    private String yljjpjsb;

    @ApiModelProperty(value = "《湖北省类培训机构教材备案表》以及培训计划、教学大纲、培训教材和其他培训材料")
    private String pxjgjcbab;

    @ApiModelProperty(value = "联合举办体育类培训机构的，应提交联合办学协议")
    private String lhjbtylpxjgxy;

    @ApiModelProperty(value = "保险合同")
    private String bxht;

    @ApiModelProperty(value = "其他")
    private String remark;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Integer createTime;  // 表中为INT类型（时间戳），对应Integer

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Integer updateTime;

    @ApiModelProperty(value = "删除时间", hidden = true)
    private Integer deleteTime;
}
