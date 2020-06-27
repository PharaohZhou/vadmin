package com.codingmc.modules.system.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName DeptDto
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/6
 * @Version V1.0
 **/
@Data
public class DeptDto implements Serializable {
    private Long id;

    private String name;

    private Long pid;

    @NotNull
    private Boolean enabled;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<DeptDto> children;

    private Timestamp createTime;

    public String getLabel() {
        return name;
    }
}
