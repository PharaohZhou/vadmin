package com.codingmc.service;

import com.codingmc.domain.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName LogService
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/5
 * @Version V1.0
 **/
public interface LogService {

    /**
     * 保存日志数据
     * @param username 用户
     * @param browser 浏览器
     * @param ip 请求IP
     * @param joinPoint /
     * @param log 日志实体
     */
    @Async
    void save(String username, String browser, String ip, ProceedingJoinPoint joinPoint, Log log);

}
