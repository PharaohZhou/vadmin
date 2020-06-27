package com.codingmc.modules.system.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName JobDto
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/8
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
public class JobDto implements Serializable {

    private Long id;

    private String name;

    private Long sort;

    @NotNull
    private Boolean enabled;

    private DeptDto dept;

    private String deptSuperiorName;

    private Timestamp createTime;

    public JobDto(String name, @NotNull Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}
