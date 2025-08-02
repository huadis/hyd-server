package cn.wuhan.hyd.system.dto;

import cn.wuhan.hyd.framework.annotation.Query;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月13日 <br>
 */
public class UserQueryReq implements Serializable {

    @Query
    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "部门ID集合")
    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds = new HashSet<>();

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "email,username,nickName")
    private String blurry;

    @Query
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @ApiModelProperty(value = "创建时间")
    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
