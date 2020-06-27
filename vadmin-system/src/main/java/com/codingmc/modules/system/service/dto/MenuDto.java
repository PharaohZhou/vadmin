package com.codingmc.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName MenuDto
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/6
 * @Version V1.0
 **/
@Data
public class MenuDto implements Serializable {

    private Long id;

    private String name;

    private String path;

    private Integer type;

    private String icon;

    private String permission;

    private Long sort;

    private String component;

    private Long pid;

    private Boolean iFrame;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private List<MenuDto> children;

    private Timestamp createTime;

}
