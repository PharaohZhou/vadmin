package com.codingmc.modules.security.security;

import com.codingmc.modules.security.config.SecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * tokenUtil
 * @ClassName TokenProvider
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/28
 * @Version V1.0
 **/
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private final SecurityProperties properties;
    private static final String AUTHORITIES_KEY = "auth";
    private Key key; //签名密匙

    public TokenProvider(SecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 创建token
     */
    public String createToken(Authentication authentication) {
        //获取用户的权限
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        //过期时间
        Date validity = new Date(now + properties.getTokenValidityInSeconds());

        return Jwts.builder() //创建token令牌
                .setSubject(authentication.getName()) //设置面向用户 --存放用户名
                .claim(AUTHORITIES_KEY, authorities) //用户角色 --添加权限属性
                .signWith(key, SignatureAlgorithm.HS512) //加密密匙和算法
                .setExpiration(validity) //过期时间
                .compact();
    }

    /**
     * 获取用户权限
     * @param token
     * @return
     */
    Authentication getAuthentication(String token) {
        //获取jwt中的信息 ---解析token的payload
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        //封装权限
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //claims.getSubject() 获取当前登录用户名
        //用户名和权限封装在User中
        User principal = new User(claims.getSubject(), "", authorities);

        //返回登录用户基本信息和角色权限信息
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);// 通过密匙验证token
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            e.printStackTrace();
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }
}
