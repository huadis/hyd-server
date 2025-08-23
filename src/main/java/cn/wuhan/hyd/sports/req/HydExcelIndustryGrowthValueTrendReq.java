package cn.wuhan.hyd.sports.req;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能说明：体育产业-总增速和增加值（年度趋势）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydExcelIndustryGrowthValueTrendReq implements Serializable {

    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @ExcelField(name = "总产值（亿元）")
    @ApiModelProperty(value = "总产值（亿元）")
    private BigDecimal totalOutputValue;

    @ExcelField(name = "增长率（%）")
    @ApiModelProperty(value = "增长率（%）")
    private BigDecimal growthRate;

    @ExcelField(name = "数据来源 / 备注")
    @ApiModelProperty(value = "数据来源/备注")
    private String dataSource;
}
