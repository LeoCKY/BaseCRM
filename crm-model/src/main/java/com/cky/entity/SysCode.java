package com.cky.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * tb_sys_code
 *
 * @author Leo Chen 2020-07-31
 */
@Data
@Table(name = "tb_sys_code")
public class SysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    /**
     * parent_id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * code
     */
    @Column(name = "code")
    private String code;

    /**
     * type
     */
    @Column(name = "type")
    private String type;

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
