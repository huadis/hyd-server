package cn.wuhan.hyd.system.mapstruct;

import cn.wuhan.hyd.framework.base.BaseMapper;
import cn.wuhan.hyd.system.domain.User;
import cn.wuhan.hyd.system.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {

}
