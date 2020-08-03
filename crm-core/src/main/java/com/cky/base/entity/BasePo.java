package com.cky.base.entity;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class BasePo {

    @Column(name = "version")
    private int version;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_ip")
    private String createIp;

    @Column(name = "is_del")
    private Boolean isDel;
}
