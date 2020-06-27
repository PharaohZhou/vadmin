package com.codingmc.modules.system.service.dto;

import com.codingmc.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName RoleQueryCriteria
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/6
 * @Version V1.0
 **/
@Data
public class RoleQueryCriteria {

    @Query(blurry = "name, remark")
    private String blurry;

    @Query(type = Query.Type.BETWEEN)
    private List<Timestamp> createTime;
}
