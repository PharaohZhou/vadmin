package com.codingmc.modules.system.service;

import com.codingmc.modules.system.domain.Role;
import com.codingmc.modules.system.service.dto.RoleDto;
import com.codingmc.modules.system.service.dto.RoleQueryCriteria;
import com.codingmc.modules.system.service.dto.RoleSmallDto;
import com.codingmc.modules.system.service.dto.UserDto;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * @ClassName RoleService
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface RoleService {
    /**
     * 获取用户权限信息
     * @param userDto 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(UserDto userDto);

    /**
     * 根据用户id查询角色
     * @param id
     * @return
     */
    List<RoleSmallDto> findByUsersId(Long id);

    /**
     * 待条件分页查询
     * @param criteria
     * @param pageable
     * @return
     */
    Object queryAll(RoleQueryCriteria criteria, Pageable pageable);

    /**
     * 根据角色查询角色级别
     * @param roles
     * @return
     */
    Integer findByRoles(Set<Role> roles);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    RoleDto findById(Long id);

    /**
     * 创建用户
     * @param resource
     * @return
     */
    RoleDto create(Role resource);
}
