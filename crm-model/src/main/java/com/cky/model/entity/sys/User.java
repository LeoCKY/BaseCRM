package com.cky.model.entity.sys;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 *  tb_user
 * @author Leo Chen 2020-07-30
 */
@Data
@Table(name = "tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * account
     */
    private String account;

    /**
     * password
     */
    private String password;

    /**
     * salt
     */
    private String salt;

    /**
     * status
     */
    private Integer status;

    /**
     * version
     */
    private Integer version;

    /**
     * create_user
     */
    private Integer createUser;

    /**
     * create_date
     */
    private Date createDate;

    /**
     * create_ip
     */
    private String createIp;

    /**
     * update_user
     */
    private Integer updateUser;

    /**
     * update_date
     */
    private Date updateDate;

    /**
     * update_ip
     */
    private String updateIp;

    /**
     * is_del
     */
    private boolean isDel;


}
