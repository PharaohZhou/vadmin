package com.codingmc.modules.system.service.dto;

import com.codingmc.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @ClassName DeptQueryCriteria
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/7
 * @Version V1.0
 **/
@Data
public class DeptQueryCriteria {

    @Query(type = Query.Type.IN, propName = "id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;

}
