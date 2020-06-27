package com.codingmc.modules.system.rest;

import com.codingmc.aop.log.Log;
import com.codingmc.config.DataScope;
import com.codingmc.modules.system.service.DeptService;
import com.codingmc.modules.system.service.JobService;
import com.codingmc.modules.system.service.dto.JobQueryCriteria;
import com.codingmc.modules.system.service.mapper.JobMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName JobContorller
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/8
 * @Version V1.0
 **/
@RestController
@RequestMapping("api/job")
@Api(tags = "系统: 岗位管理")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private DataScope dataScope;

    @Log("查询岗位")
    @ApiOperation("查询岗位")
    @GetMapping
    @PreAuthorize("@v.check('user:list','job:list')")
    public ResponseEntity<Object> getJobs(JobQueryCriteria queryCriteria, Pageable pageable) {
        // 数据权限
        queryCriteria.setDeptIds(dataScope.getDeptIds());
        return new ResponseEntity<>(jobService.queryAll(queryCriteria,pageable), HttpStatus.OK);
    }
}
