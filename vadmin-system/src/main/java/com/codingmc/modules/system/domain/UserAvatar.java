package com.codingmc.modules.system.domain;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName UserAvatar
 * @Description: TODO
 * @Author zhou
 * @Date 2020/4/29
 * @Version V1.0
 **/
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_avatar")
public class UserAvatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realName;

    private String path;

    private String size;

    @Column(name = "create_time")
    private Timestamp createTime;

    public UserAvatar(UserAvatar userAvatar, String realName, String path, String size) {
        this.id = ObjectUtil.isNotEmpty(userAvatar) ? userAvatar.getId() : null;
        this.realName = realName;
        this.path = path;
        this.size = size;
    }
}
