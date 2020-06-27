package com.codingmc.modules.system.rest;

import com.codingmc.aop.log.Log;
import com.codingmc.exception.BadRequestException;
import com.codingmc.modules.system.domain.Role;
import com.codingmc.modules.system.service.RoleService;
import com.codingmc.modules.system.service.dto.RoleQueryCriteria;
import com.codingmc.modules.system.service.dto.RoleSmallDto;
import com.codingmc.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.stream.Collectors;


/**
 * @ClassName RoleController
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/6
 * @Version V1.0
 **/
@Api(tags = "系统: 角色管理")
@RestController
@RequestMapping("api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static final String ENTITY_NAME = "role";

    @Log("查询角色")
    @ApiOperation("查询角色")
    @GetMapping
    @PreAuthorize("@v.check('role:list')")
    public ResponseEntity<Object> getRoles(RoleQueryCriteria queryCriteria, Pageable pageable) {
        return new ResponseEntity<>(roleService.queryAll(queryCriteria, pageable), HttpStatus.OK);
    }

    @Log("新增角色")
    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("@v.check('role:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Role resource) {
        if (resource.getId() != null) {
            throw new BadRequestException("A new " + ENTITY_NAME + " cannot already have an ID");
        }
        getLevel(resource.getLevel());

        return new ResponseEntity<>(roleService.create(resource),HttpStatus.OK);
    }

    private int getLevel(Integer level) {
        Integer currentLevel = Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId())
                .stream()
                .map(RoleSmallDto::getLevel)
                .collect(Collectors.toList()));
        if (level != null) {
            if (currentLevel > level) {
                throw new BadRequestException("权限不足，你的角色级别：" + currentLevel + "，低于操作的角色级别：" + level);
            }
        }
        return currentLevel;
    }
}
