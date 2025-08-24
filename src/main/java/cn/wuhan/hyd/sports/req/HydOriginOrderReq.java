package cn.wuhan.hyd.sports.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能说明：订单表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Data
public class HydOriginOrderReq implements Serializable {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "活动id")
    private String activityId;

    @ApiModelProperty(value = "组织id")
    private String tenantId;

    @ApiModelProperty(value = "场馆id")
    private String stadiumId;

    @ApiModelProperty(value = "项目id")
    private String stadiumItemId;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "订单金额，单位分")
    private Integer orderAmount;  // 表中为int类型，对应Integer

    @ApiModelProperty(value = "实付金额，单位分")
    private Integer actualAmount;

    @ApiModelProperty(value = "消费券金额，单位分")
    private Integer couponAmount;

    @ApiModelProperty(value = "退款金额，单位分")
    private Integer refundAmount;

    @ApiModelProperty(value = "是否使用消费券")
    private Integer useCoupon;  // 表中为tinyint类型，对应Integer

    @ApiModelProperty(value = "用户年龄")
    private Integer userAge;

    @ApiModelProperty(value = "用户性别")
    private String userGender;

    @ApiModelProperty(value = "学员年龄")
    private Integer studentAge;

    @ApiModelProperty(value = "学员性别")
    private String studentGender;

    @ApiModelProperty(value = "学员所属区域")
    private String studentRegion;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createdTime;

    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updatedTime;
}
