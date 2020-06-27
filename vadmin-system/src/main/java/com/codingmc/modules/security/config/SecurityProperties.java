package com.codingmc.modules.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt参数配置
 * @ClassName SecurityProperties
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/28
 * @Version V1.0
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class SecurityProperties {

    //Request Headers : Authorization
    private String header;

    //令牌前缀,最后留个空格 Bearer
    private String tokenStartWith;

    //必须使用最少88位的Base64对该令牌进行编码
    private String base64Secret;

    //令牌过期时间, 此处为单位/毫秒
    private Long tokenValidityInSeconds;

    //在线用户key,根据key查询redis中在线用户的数据
    private String onlineKey;

    //验证码Key
    private String codeKey;

    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }
}
