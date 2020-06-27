package com.codingmc.modules.system.service.dto;

import com.codingmc.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserQueryCriteria
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/28
 * @Version V1.0
 **/
@Data
public class UserQueryCriteria {

    @Query
    private Long id;

    @Query(propName = "id", type = Query.Type.IN, joinName = "dept")
    private Set<Long> deptIds;

    @Query(blurry = "email, username, nickName")
    private String blurry;

    @Query
    private Boolean enabled;

    private Long deptId;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
