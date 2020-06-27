package com.codingmc.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName DeptSmallDto
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
@Data
public class DeptSmallDto implements Serializable {

    private Long id;

    private String name;
}
