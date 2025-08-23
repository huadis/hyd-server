package cn.wuhan.hyd.sports.req;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能说明：体育产业-场主体数量（分类占比）表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydExcelIndustryEntityCountRatioReq implements Serializable {

    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @ExcelField(name = "市场主体类型（如体育管理活动 / 竞赛表演等）")
    @ApiModelProperty(value = "市场主体类型（如体育管理活动 / 竞赛表演等）")
    private String entityType;

    @ExcelField(name = "占比（%）")
    @ApiModelProperty(value = "占比（%）")
    private BigDecimal proportion;

    @ExcelField(name = "数据来源 / 备注")
    @ApiModelProperty(value = "数据来源/备注")
    private String dataSource;
}
