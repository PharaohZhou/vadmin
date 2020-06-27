package com.codingmc.modules.system.service;

import com.codingmc.modules.system.domain.Dept;
import com.codingmc.modules.system.service.dto.DeptDto;
import com.codingmc.modules.system.service.dto.DeptQueryCriteria;

import java.util.List;
import java.util.Set;

/**
 * @ClassName DeptService
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
public interface DeptService {

    /**
     * 根据角色id查询
     * @param id
     */
    Set<Dept> findByRoleIds(Long id);

    /**
     * 根据id查询子部门
     * @param id
     * @return
     */
    List<Dept> findByPid(Long pId);

    /**
     * 查询所有部门
     * @param criteria
     * @return
     */
    List<DeptDto> queryAll(DeptQueryCriteria criteria);

    /**
     * 构建树形数据
     * @param deptDtos 原始数据
     * @return
     */
    Object buildTree(List<DeptDto> deptDtos);
}
