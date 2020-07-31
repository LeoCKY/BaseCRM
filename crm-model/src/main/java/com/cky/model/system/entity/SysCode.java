package com.cky.model.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  tb_sys_code
 * @author Leo Chen 2020-07-31
 */
@Entity
@Data
@Table(name="tb_sys_code")
public class SysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue

    /**
     * parent_id
     */
    @Column(name="parent_id")
    private Integer parentId;

    /**
     * code
     */
    @Column(name="code")
    private String code;

    /**
     * type
     */
    @Column(name="type")
    private String type;

    /**
     * description
     */
    @Column(name="description")
    private String description;

    /**
     * version
     */
    @Column(name="version")
    private Integer version;

    /**
     * create_user
     */
    @Column(name="create_user")
    private Integer createUser;

    /**
     * create_date
     */
    @Column(name="create_date")
    private Date createDate;

    /**
     * create_ip
     */
    @Column(name="create_ip")
    private String createIp;

    /**
     * update_user
     */
    @Column(name="update_user")
    private Integer updateUser;

    /**
     * update_date
     */
    @Column(name="update_date")
    private Date updateDate;

    /**
     * update_ip
     */
    @Column(name="update_ip")
    private String updateIp;

    /**
     * is_del
     */
    @Column(name="is_del")
    private boolean isDel;

}
