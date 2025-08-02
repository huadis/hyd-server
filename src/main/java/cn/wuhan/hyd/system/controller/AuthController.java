package cn.wuhan.hyd.system.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousDeleteMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.exception.BadRequestException;
import cn.wuhan.hyd.framework.security.JwtUserDto;
import cn.wuhan.hyd.framework.security.SecurityProperties;
import cn.wuhan.hyd.framework.security.TokenProvider;
import cn.wuhan.hyd.framework.utils.SecurityUtils;
import cn.wuhan.hyd.system.dto.AuthUserDto;
import cn.wuhan.hyd.system.dto.UserDto;
import cn.wuhan.hyd.system.service.impl.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明： 授权、根据token获取用户详细信息 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "系统：系统授权接口")
public class AuthController {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private TokenProvider tokenProvider;
    @Resource
    private UserDetailsServiceImpl userDetailsService;
    @Resource
    private SecurityProperties properties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public Response<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 获取用户信息
        JwtUserDto<UserDto> jwtUser = userDetailsService.loadUserByUsername(authUser.getUsername());
        // 验证用户密码
        if (!passwordEncoder.matches(authUser.getPassword(), jwtUser.getPassword())) {
            throw new BadRequestException("登录密码错误");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = tokenProvider.createToken(jwtUser);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUser);
        }};
        // 返回登录信息
        return Response.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public Response<UserDetails> getUserInfo() {
        return Response.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public Response<Object> logout(HttpServletRequest request) {
        return Response.ok();
    }
}
