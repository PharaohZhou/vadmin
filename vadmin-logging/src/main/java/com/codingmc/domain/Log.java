package com.codingmc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @ClassName Log
 * @Description: TODO
 * @Author zhou
 * @Date 2020/6/5
 * @Version V1.0
 **/
@Entity
@Data
@Table(name = "log")
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    /**
     * 描述
     */
    private String description;

    @Column(name = "exception_detail",columnDefinition = "text")
    private byte[] exceptionDetail;

    @Column(name = "log_type")
    private String logType;

    private String method;

    @Column(columnDefinition = "text")
    private String params;

    @Column(name = "request_ip")
    private String requestIp;

    private Long time;

    /**
     * 操作用户
     */
    private String username;

    private String address;

    private String browser;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}

