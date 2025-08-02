package cn.wuhan.hyd.system.service.impl;

import cn.wuhan.hyd.framework.exception.BadRequestException;
import cn.wuhan.hyd.framework.security.JwtUserDto;
import cn.wuhan.hyd.system.dto.UserDto;
import cn.wuhan.hyd.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public JwtUserDto<UserDto> loadUserByUsername(String username) {
        UserDto user = userService.getLoginData(username);
        if (user == null) {
            throw new BadRequestException("用户不存在");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活！");
            }
            // 初始化JwtUserDto
            return new JwtUserDto<UserDto>(user);
        }
    }
}
