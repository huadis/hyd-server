package cn.wuhan.hyd.system.dto;

import cn.wuhan.hyd.framework.base.BaseDTO;
import cn.wuhan.hyd.framework.security.JwtUserInfo;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@Getter
@Setter
public class UserDto extends BaseDTO implements JwtUserInfo, Serializable {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "头像")
    private String avatarName;

    @ApiModelProperty(value = "头像路径")
    private String avatarPath;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "管理员")
    @JSONField(serialize = false)
    private Boolean isAdmin = false;

    @ApiModelProperty(value = "密码重置时间")
    private Date pwdResetTime;
}
