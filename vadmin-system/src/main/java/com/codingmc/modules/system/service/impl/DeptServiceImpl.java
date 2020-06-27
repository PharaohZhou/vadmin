package com.codingmc.modules.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.codingmc.modules.system.domain.Dept;
import com.codingmc.modules.system.repository.DeptRepository;
import com.codingmc.modules.system.service.DeptService;
import com.codingmc.modules.system.service.dto.DeptDto;
import com.codingmc.modules.system.service.dto.DeptQueryCriteria;
import com.codingmc.modules.system.service.mapper.DeptMapper;
import com.codingmc.utils.QueryHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName DeptServiceImpl
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Service
@CacheConfig(cacheNames = "dept")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return deptRepository.findByRoles_Id(id);
    }

    @Override
    public List<Dept> findByPid(Long pId) {
        return deptRepository.findByPid(pId);
    }

    @Override
    @Cacheable
    public List<DeptDto> queryAll(DeptQueryCriteria criteria) {
        return deptMapper.toDto(deptRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Cacheable
    public Object buildTree(List<DeptDto> deptDtos) {
        Set<DeptDto> trees = new LinkedHashSet<>();
        Set<DeptDto> depts = new LinkedHashSet<>();
        List<String> deptNames = deptDtos.stream().map(DeptDto::getName).collect(Collectors.toList());
        boolean isChild;
        for (DeptDto deptDto : deptDtos) {
            isChild = false;
            if ("0".equals(deptDto.getPid().toString())) {
                trees.add(deptDto);
            }
            for (DeptDto dept : deptDtos) {
                if (dept.getPid().equals(deptDto.getId())) {
                    isChild = true;
                    if (deptDto.getChildren() == null) {
                        deptDto.setChildren(new ArrayList<>());
                    }
                    deptDto.getChildren().add(dept);
                }
            }
            if (isChild) {
                depts.add(deptDto);
            } else if (!deptNames.contains(deptRepository.findNameById(deptDto.getPid()))) {
                depts.add(deptDto);
            }
        }
        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDtos.size();

        Map<String,Object> map = new HashMap<>(2);
        map.put("totalElements",totalElements);
        map.put("content", trees);
        return map;
    }
}
