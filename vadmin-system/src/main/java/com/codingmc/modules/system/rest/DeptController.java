package com.codingmc.modules.system.rest;

import com.codingmc.aop.log.Log;
import com.codingmc.config.DataScope;
import com.codingmc.modules.system.service.DeptService;
import com.codingmc.modules.system.service.dto.DeptDto;
import com.codingmc.modules.system.service.dto.DeptQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DeptController
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/7
 * @Version V1.0
 **/
@RestController
@Api(tags = "系统:部门管理")
@RequestMapping("api/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DataScope dataScope;

    private static final String ENTITY_NAME = "dept";

    @Log("查询部门")
    @ApiOperation("查询部门")
    @GetMapping
    @PreAuthorize("@v.check('user:list','dept:list')")
    public ResponseEntity<Object> getDepts(DeptQueryCriteria criteria) {
        // 数据权限
        criteria.setIds(dataScope.getDeptIds());
        List<DeptDto> deptDtos = deptService.queryAll(criteria);
        return new ResponseEntity<>(deptService.buildTree(deptDtos), HttpStatus.OK);
    }
}
