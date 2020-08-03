package com.cky.model.system.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * tb_permission
 *
 * @author Leo Chen 2020-07-31
 */
@Data
@Table(name = "tb_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;
    /**
     * menu_id
     */
    @Column(name = "menu_id")
    private Integer menuId;

    /**
     * permission
     */
    @Column(name = "permission")
    private String permission;

    /**
     * description
     */
    @Column(name = "description")
    private String description;

    /**
     * version
     */
    @Column(name = "version")
    private Integer version;

    /**
     * create_user
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * create_date
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * create_ip
     */
    @Column(name = "create_ip")
    private String createIp;

    /**
     * update_user
     */
    @Column(name = "update_user")
     private String updateUser;

    /**
     * update_date
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * update_ip
     */
    @Column(name = "update_ip")
    private String updateIp;

    /**
     * is_del
     */
    @Column(name = "is_del")
    private Boolean isDel;


}
