package com.cky.model.system.entity;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 *  tb_city
 * @author Leo Chen 2020-07-31
 */
@Entity
@Data
@Table(name="tb_city")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue

    /**
     * state_id
     */
    @Column(name="state_id")
    private Integer stateId;

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
