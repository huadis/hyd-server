package cn.wuhan.hyd.framework.security;

import cn.hutool.core.util.IdUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月13日 <br>
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private Key signingKey;
    private JwtParser jwtParser;
    private final SecurityProperties properties;
    public static final String AUTHORITIES_UUID_KEY = "uid";
    public static final String AUTHORITIES_UID_KEY = "userId";

    public TokenProvider(SecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() {
        // 解码Base64密钥并创建签名密钥
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        // 初始化 JwtParser
        jwtParser = Jwts.parserBuilder()
                .setSigningKey(signingKey) // 使用预生成的签名密钥
                .build();
    }

    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     *
     * @param user /
     * @return /
     */
    public String createToken(JwtUserDto user) {
        // 设置参数
        Map<String, Object> claims = new HashMap<>(6);
        // 设置用户ID
        claims.put(AUTHORITIES_UID_KEY, user.getUser().getId());
        // 设置UUID，确保每次Token不一样
        claims.put(AUTHORITIES_UUID_KEY, IdUtil.simpleUUID());
        // 直接调用 Jwts.builder() 创建新实例
        return Jwts.builder()
                // 设置自定义 Claims
                .setClaims(claims)
                // 设置主题
                .setSubject(user.getUsername())
                // 使用预生成的签名密钥和算法签名
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token /
     * @return /
     */
    Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        User principal = new User(claims.getSubject(), "******", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<>());
    }

    public Claims getClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }

    /**
     * 获取登录用户RedisKey
     * @param token /
     * @return key
     */
    public String loginKey(String token) {
        Claims claims = getClaims(token);
        return properties.getOnlineKey() + claims.getSubject() + ":" + getId(token);
    }

    /**
     * 获取登录用户TokenKey
     * @param token /
     * @return /
     */
    public String getId(String token) {
        Claims claims = getClaims(token);
        return claims.get(AUTHORITIES_UUID_KEY).toString();
    }
}
