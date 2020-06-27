package com.codingmc.config;

import com.codingmc.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName PermissionConfig
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/29
 * @Version V1.0
 **/
@Service(value = "v")
public class PermissionConfig {
    public Boolean check(String... permissions) {
        // 获取当前用户的所有权限
        List<String> vPermissions = SecurityUtils.getCurrentUser()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return vPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(vPermissions::contains);
    }
}
