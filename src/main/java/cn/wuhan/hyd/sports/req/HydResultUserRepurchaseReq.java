package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-场馆预定-复购率 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultUserRepurchaseReq implements Serializable {

    @ApiModelProperty(value = "一次下单")
    private String onceNum;  // 变量名调整为符合Java命名规范，通过@Column映射到数据库字段"1Num"

    @ApiModelProperty(value = "2-5次")
    private String bt2and5Num;

    @ApiModelProperty(value = "5次以上")
    private String over5Num;

    @ApiModelProperty(value = "10次以上")
    private String over10Num;

    @ApiModelProperty(value = "50次以上")
    private String over50Num;
}
