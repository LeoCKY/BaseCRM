package com.cky.model.system.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * tb_user_info
 *
 * @author Leo Chen 2020-07-31
 */
@Entity
@Data
@Table(name = "tb_user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue

    /**
     * user_id
     */
    private Integer userId;

    /**
     * f_name
     */
    private String fName;

    /**
     * l_name
     */
    @Column(name="l_name")
    private String lName;

    /**
     * email
     */
    @Column(name="email")
    private String email;

    /**
     * phone
     */
    @Column(name="phone")
    private String phone;

    /**
     * birthday
     */
    @Column(name="birthday")
    private Date birthday;

    /**
     * id_num
     */
    @Column(name="id_num")
    private String idNum;

    /**
     * country_code
     */
    @Column(name="country_code")
    private String countryCode;

    /**
     * city_code
     */
    @Column(name="city_code")
    private String cityCode;

    /**
     * address
     */
    @Column(name="address")
    private String address;

    /**
     * postcode
     */
    @Column(name="postcode")
    private Integer postcode;

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
