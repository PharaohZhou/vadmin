package com.codingmc.config;

import com.codingmc.modules.system.domain.Dept;
import com.codingmc.modules.system.service.DeptService;
import com.codingmc.modules.system.service.RoleService;
import com.codingmc.modules.system.service.UserService;
import com.codingmc.modules.system.service.dto.RoleSmallDto;
import com.codingmc.modules.system.service.dto.UserDto;
import com.codingmc.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 数据权限配置
 * @ClassName DataScope
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Component
public class DataScope {

    private final String[] scopeType = {"全部", "本级", "自定义"};

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DeptService deptService;

    public Set<Long> getDeptIds() {

        UserDto user = userService.findByName(SecurityUtils.getCurrentUsername());

        // 用于存储部门id
        Set<Long> deptIds = new HashSet<>();

        // 查询用户角色
        List<RoleSmallDto> roleSet = roleService.findByUsersId(user.getId());

        for (RoleSmallDto role : roleSet) {
            if (scopeType[0].equals(role.getDataScope())) {
                return new HashSet<>();
            }

            // 存储本级的数据权限
            if (scopeType[1].equals(role.getDataScope())) {
                deptIds.add(user.getDept().getId());
            }

            // 存储自定义的数据权限
            if (scopeType[2].equals(role.getDataScope())) {
                Set<Dept> depts = deptService.findByRoleIds(role.getId());
                for (Dept dept : depts) {
                    deptIds.add(dept.getId());
                    List<Dept> deptChildren = deptService.findByPid(dept.getId());
                    if (deptChildren != null && deptChildren.size() != 0) {
                        deptIds.addAll(getDeptChildren(deptChildren));
                    }
                }
            }
        }
        return deptIds;
    }

    public List<Long> getDeptChildren(List<Dept> deptList) {
        List<Long> list = new ArrayList<>();
        deptList.forEach(dept -> {
            if (dept != null && dept.getEnabled()) {
                List<Dept> depts = deptService.findByPid(dept.getPid());
                if (depts.size() != 0) {
                    list.addAll(getDeptChildren(depts));
                }
                list.add(dept.getId());
            }
        });
        return list;
    }
}
