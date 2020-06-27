package com.codingmc.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @ClassName RoleDto
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/6
 * @Version V1.0
 **/
@Data
public class RoleDto implements Serializable {
    private Long id;

    private String name;

    private String dataScope;

    private Integer level;

    private String remark;

    private String permission;

    private Set<MenuDto> menus;

    private Set<DeptDto> depts;

    private Timestamp createTime;
}
