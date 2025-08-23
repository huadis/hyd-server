package cn.wuhan.hyd.sports.req;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：体育产业-从业人员数量（分类统计）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Data
public class HydExcelIndustryEmployeeCountReq implements Serializable {

    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @ExcelField(name = "从业类型（体育管理活动 / 竞赛表演等）")
    @ApiModelProperty(value = "从业类型（体育管理活动 / 竞赛表演等）")
    private String entityType;

    @ExcelField(name = "人数（人）")
    @ApiModelProperty(value = "人数（人）")
    private Integer personCount;

    @ExcelField(name = "数据来源 / 备注")
    @ApiModelProperty(value = "数据来源/备注")
    private String dataSource;
}

