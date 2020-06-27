package com.codingmc.modules.system.service.impl;

import com.codingmc.exception.EntityExistException;
import com.codingmc.modules.system.domain.Menu;
import com.codingmc.modules.system.domain.Role;
import com.codingmc.modules.system.repository.RoleRepository;
import com.codingmc.modules.system.service.RoleService;
import com.codingmc.modules.system.service.dto.RoleDto;
import com.codingmc.modules.system.service.dto.RoleQueryCriteria;
import com.codingmc.modules.system.service.dto.RoleSmallDto;
import com.codingmc.modules.system.service.dto.UserDto;
import com.codingmc.modules.system.service.mapper.RoleMapper;
import com.codingmc.modules.system.service.mapper.RoleSmallMapper;
import com.codingmc.utils.PageUtil;
import com.codingmc.utils.QueryHelp;
import com.codingmc.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName RoleServiceImpl
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
@Service
@CacheConfig(cacheNames = "role")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleSmallMapper roleSmallMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Cacheable(key = "'loadPermissionByUser:' + #p0.username")
    public List<GrantedAuthority> mapToGrantedAuthorities(UserDto userDto) {
        Set<Role> roles = roleRepository.findByUsers_Id(userDto.getId());
        Set<String> permissions = roles.stream()
                .filter(role -> StringUtils.isNotBlank(role.getPermission()))
                .map(Role::getPermission)
                .collect(Collectors.toSet());
        permissions.addAll(
                roles.stream()
                        .flatMap(role -> role.getMenus().stream())
                        .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                        .map(Menu::getPermission)
                        .collect(Collectors.toSet())
        );
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    @Cacheable(key = "'findByUsers_Id:' + #p0")
    public List<RoleSmallDto> findByUsersId(Long id) {
        return roleSmallMapper.toDto(new ArrayList<>(roleRepository.findByUsers_Id(id)));
    }

    @Override
    @Cacheable
    public Object queryAll(RoleQueryCriteria criteria, Pageable pageable) {
        Page<Role> page = roleRepository.findAll(
                (root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)
                , pageable);
        return PageUtil.toPage(page.map(roleMapper::toDto));
    }

    @Override
    @Cacheable
    public Integer findByRoles(Set<Role> roles) {
        Set<RoleDto> roleDtos = new HashSet<>();
        for (Role role : roles) {
            roleDtos.add(findById(role.getId()));
        }
        return Collections.min(roleDtos.stream().map(RoleDto::getLevel).collect(Collectors.toList()));
    }

    @Override
    @Cacheable(key = "#p0")
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).orElseGet(Role::new);
        ValidationUtil.isNull(role.getId(), "Role", "id", id);
        return roleMapper.toDto(role);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public RoleDto create(Role resources) {
        if(roleRepository.findByName(resources.getName()) != null){
            throw new EntityExistException(Role.class,"username",resources.getName());
        }
        Role save = roleRepository.save(resources);
        return roleMapper.toDto(save);
    }
}
