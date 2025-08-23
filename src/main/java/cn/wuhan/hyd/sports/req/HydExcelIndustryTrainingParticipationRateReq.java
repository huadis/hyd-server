package cn.wuhan.hyd.sports.req;

import cn.wuhan.hyd.framework.annotation.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 功能说明：体育产业-居民体育培训项目参与率表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydExcelIndustryTrainingParticipationRateReq implements Serializable {

    @Column(name = "statisticalYear")
    @ExcelField(name = "统计年度")
    @ApiModelProperty(value = "统计年度")
    private Integer statisticalYear;

    @Column(name = "entityType")
    @ExcelField(name = "培训项目（帆船、皮筏艇 / 羽毛球等）")
    @ApiModelProperty(value = "培训项目（帆船、皮筏艇 / 羽毛球等）")
    private String entityType;

    @Column(name = "growthRate")
    @ExcelField(name = "参与率（%）")
    @ApiModelProperty(value = "参与率（%）")
    private BigDecimal growthRate;

    @Column(name = "dataSource")
    @ExcelField(name = "数据来源 / 备注")
    @ApiModelProperty(value = "数据来源/备注")
    private String dataSource;
}
