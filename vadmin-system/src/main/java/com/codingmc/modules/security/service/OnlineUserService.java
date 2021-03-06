package com.codingmc.modules.security.service;

import com.codingmc.modules.security.config.SecurityProperties;
import com.codingmc.modules.security.service.dto.JwtUserDto;
import com.codingmc.modules.security.service.dto.OnlineUserDto;
import com.codingmc.utils.EncryptUtils;
import com.codingmc.utils.RedisUtils;
import com.codingmc.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OnlineUserService
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/12
 * @Version V1.0
 **/
@Service
@Slf4j
public class OnlineUserService {

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询用户
     * @param key
     * @return
     */
    public OnlineUserDto getOne(String key) {
        return (OnlineUserDto) redisUtils.get(key);
    }

    /**
     * 保存在线用户信息
     * @param jwtUserDto
     * @param token
     * @param request
     */
    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {
        String job = jwtUserDto.getUser().getDept().getName() + "/" + jwtUserDto.getUser().getJob().getName();
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(
                    jwtUserDto.getUsername(),
                    jwtUserDto.getUser().getNickName(),
                    job, browser, ip, address,
                    EncryptUtils.desEncrypt(token),
                    new Date()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        redisUtils.set(properties.getOnlineKey() + token, onlineUserDto, properties.getTokenValidityInSeconds()/1000);
    }

    /**
     * 查询所有数据,不分页
     * @param filter
     * @return
     */
    public List<OnlineUserDto> getAll(String filter) {
        List<String> keys = redisUtils.scan(properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDto onlineUserDto = (OnlineUserDto) redisUtils.get(key);
            if (StringUtils.isNotBlank(filter)) {
                if (onlineUserDto.toString().contains(filter)) {
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort(((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime())));
        return onlineUserDtos;
    }


    /**
     * 检测用户是否在之前已经登录,已经登录则踢下线
     * @param username
     * @param igoreToken
     */
    public void checkLoginOnUser(String username, String igoreToken) {
        List<OnlineUserDto> onlineUserDtos = getAll(username);
        if (onlineUserDtos == null || onlineUserDtos.isEmpty()) {
            return;
        }
        for (OnlineUserDto onlineUserDto : onlineUserDtos) {
            if (onlineUserDto.getUserName().equals(username)) {
                try {
                    String token = EncryptUtils.desDecrypt(onlineUserDto.getKey());

                    if (StringUtils.isNotBlank(igoreToken) && !igoreToken.equals(token)) {
                        this.kickOut(token);
                    } else if (StringUtils.isBlank(igoreToken)) {
                        this.kickOut(token);
                    }

                } catch (Exception e) {
                    log.error("checkUser is error", e);
                }
            }
        }
    }

    /**
     * 踢出用户
     * @param key
     */
    private void kickOut(String key) {
        key = properties.getOnlineKey() + key;
        redisUtils.del(key);
    }

    /**
     * 退出登录
     * @param token
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);

    }
}
