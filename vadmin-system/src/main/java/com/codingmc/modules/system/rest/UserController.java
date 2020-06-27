package com.codingmc.modules.system.rest;

import com.codingmc.aop.log.Log;
import com.codingmc.config.DataScope;
import com.codingmc.exception.BadRequestException;
import com.codingmc.modules.system.domain.User;
import com.codingmc.modules.system.service.DeptService;
import com.codingmc.modules.system.service.RoleService;
import com.codingmc.modules.system.service.UserService;
import com.codingmc.modules.system.service.dto.RoleSmallDto;
import com.codingmc.modules.system.service.dto.UserQueryCriteria;
import com.codingmc.utils.PageUtil;
import com.codingmc.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName UserController
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Api(tags = "系统: 用户管理")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private DataScope dataScope;

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Log("查询用户")
    @ApiOperation("查询用户")
    @GetMapping
    @PreAuthorize("@v.check('user:list')")
    public ResponseEntity<Object> getUsers(UserQueryCriteria criteria, Pageable pageable) {
        Set<Long> deptSet = new HashSet<>();
        Set<Long> result = new HashSet<>();
        if (!ObjectUtils.isEmpty(criteria.getDeptId())) {
            deptSet.add(criteria.getDeptId());
            deptSet.addAll(dataScope.getDeptChildren(deptService.findByPid(criteria.getDeptId())));
        }
        // 数据权限
        Set<Long> deptIds = dataScope.getDeptIds();
        // 查询条件不为空并且数据权限不为空则取交集
        if (!CollectionUtils.isEmpty(deptIds) && !CollectionUtils.isEmpty(deptSet)) {
            // 取交集
            result.addAll(deptSet);
            result.retainAll(deptIds);
            if (result.size() == 0) {
                return new ResponseEntity<>(PageUtil.toPage(null, 0), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
            }
            // 否则取并集 admin
        } else {
            result.addAll(deptSet);
            result.addAll(deptIds);
            criteria.setDeptIds(result);
            return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);
        }
    }

    @Log("新增用户")
    @ApiOperation("新增用户")
    @PostMapping
    @PreAuthorize("@v.check('user:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody User resources) {
        checkLevel(resources);
        // 默认密码123456
        resources.setPassword(passwordEncoder.encode("123456"));
        return new ResponseEntity<>(userService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改用户")
    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("@v.check('user:edit')")
    public ResponseEntity<Object> update(@Validated(User.Update.class) @RequestBody User resources) {
        checkLevel(resources);
        userService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除用户")
    @ApiOperation("删除用户")
    @DeleteMapping
    @PreAuthorize("@v.check('user:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        for (Long id : ids) {
            Integer currentLevel = Collections.min(
                    roleService.findByUsersId(SecurityUtils.getCurrentUserId())
                    .stream()
                    .map(RoleSmallDto::getLevel)
                    .collect(Collectors.toList()));
            Integer optLevel = Collections.min(
                    roleService.findByUsersId(id)
                    .stream()
                    .map(RoleSmallDto::getLevel)
                    .collect(Collectors.toList()));
            if (currentLevel > optLevel) {
                throw new BadRequestException("角色权限不足, 不能删除!" + userService.findById(id).getUsername());
            }
        }
        userService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 如果当前用户创建一个用户的角色级别高于当前用户的角色级别,则抛出权限不住的异常
     * @param resources
     */
    private void checkLevel(User resources) {
        Integer currentLevel = Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId())
                .stream()
                .map(RoleSmallDto::getLevel)
                .collect(Collectors.toList()));
        Integer optLevel = roleService.findByRoles(resources.getRoles());
        if (currentLevel > optLevel) {
            throw new BadRequestException("角色权限不足");
        }

    }
}
