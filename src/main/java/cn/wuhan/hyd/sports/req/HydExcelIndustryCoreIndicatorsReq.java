package cn.wuhan.hyd.sports.req;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能说明：体育产业-核心指标总表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydExcelIndustryCoreIndicatorsReq implements Serializable {

    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @ExcelField(name = "体育产业总产值（亿）")
    @ApiModelProperty(value = "体育产业总产值（亿）")
    private BigDecimal totalOutputValue;

    @ExcelField(name = "产业增加值（亿）")
    @ApiModelProperty(value = "产业增加值（亿）")
    private BigDecimal addedValue;

    @ExcelField(name = "体育市场主体总量（家）")
    @ApiModelProperty(value = "体育市场主体总量（家）")
    private Integer marketEntityCount;

    @ExcelField(name = "从业人员数量（万）")
    @ApiModelProperty(value = "从业人员数量（万）")
    private BigDecimal employeeCount;

    @ExcelField(name = "人均体育消费（元）")
    @ApiModelProperty(value = "人均体育消费（元）")
    private Integer perCapitaSportsConsumption;

    @ExcelField(name = "人均体育场地面积（㎡）")
    @ApiModelProperty(value = "人均体育场地面积（㎡）")
    private BigDecimal perCapitaSportsArea;

    @ExcelField(name = "重大项目签约（亿）")
    @ApiModelProperty(value = "重大项目签约（亿）")
    private BigDecimal majorProjectContract;

    @ExcelField(name = "备注")
    @ApiModelProperty(value = "备注")
    private String remark;
}
