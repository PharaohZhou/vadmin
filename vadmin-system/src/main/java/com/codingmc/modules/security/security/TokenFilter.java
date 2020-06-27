package com.codingmc.modules.security.security;

import com.codingmc.modules.security.config.SecurityProperties;
import com.codingmc.modules.security.service.OnlineUserService;
import com.codingmc.modules.security.service.dto.OnlineUserDto;
import com.codingmc.utils.SpringContextHolder;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName TokenFilter
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
@Slf4j
public class TokenFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    public TokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //获取token 并去掉前缀
        String token = resolveToken(httpServletRequest);
        String requestRri = httpServletRequest.getRequestURI();
        // 验证 token 是否存在
        OnlineUserDto onlineUserDto = null;

        try {
            SecurityProperties properties = SpringContextHolder.getBean(SecurityProperties.class);
            OnlineUserService onlineUserService = SpringContextHolder.getBean(OnlineUserService.class);
            onlineUserDto = onlineUserService.getOne(properties.getOnlineKey() + token);
        } catch (ExpiredJwtException e) {
            log.error(e.getMessage());
        }
        if (onlineUserDto != null && StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
        }else {
            log.debug("no valid JWT token found, uri: {}", requestRri);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 将令牌的前缀去掉
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        SecurityProperties properties = SpringContextHolder.getBean(SecurityProperties.class);
        String bearerToken = request.getHeader(properties.getHeader());
        //String.startsWith(String s)查询字符串是否由s前缀开始
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            // 去除令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(), "");
        }
        return null;
    }
}
