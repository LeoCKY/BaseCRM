package com.cky.model.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  tb_country
 * @author Leo Chen 2020-07-31
 */
@Entity
@Data
@Table(name="tb_country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue

    /**
     * name
     */
    @Column(name="name")
    private String name;

    /**
     * code
     */
    @Column(name="code")
    private String code;

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
