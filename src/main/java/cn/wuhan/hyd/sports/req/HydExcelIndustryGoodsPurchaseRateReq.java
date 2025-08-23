package cn.wuhan.hyd.sports.req;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能说明：体育产业-居民体育用品购买率表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Data
public class HydExcelIndustryGoodsPurchaseRateReq implements Serializable {

    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @ExcelField(name = "体育用品类型（运动鞋 / 运动服饰等）")
    @ApiModelProperty(value = "体育用品类型（运动鞋 / 运动服饰等）")
    private String entityType;

    @ExcelField(name = "购买率（%）")
    @ApiModelProperty(value = "购买率（%）")
    private BigDecimal growthRate;

    @ExcelField(name = "数据来源 / 备注")
    @ApiModelProperty(value = "数据来源/备注")
    private String dataSource;
}
