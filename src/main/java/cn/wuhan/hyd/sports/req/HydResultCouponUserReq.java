package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 功能说明：结果表-体育消费卷-券用户分析 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Data
public class HydResultCouponUserReq implements Serializable {

    @ApiModelProperty(value = "领券人数")
    private String receiveCouponNum;

    @ApiModelProperty(value = "用券人数")
    private String useCouponNum;

    @ApiModelProperty(value = "男性人数")
    private String maleNum;

    @ApiModelProperty(value = "女性人数")
    private String femaleNum;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "分组id")
    private String groupId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "类型")
    private String type;
}
