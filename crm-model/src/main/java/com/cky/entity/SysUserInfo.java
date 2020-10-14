package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *  tb_sys_user_info
 * @author Leo Chen 2020-08-28
 */
@Entity
@Data
@Table(name="tb_sys_user_info")
@ApiModel("tb_sys_user_info")
public class SysUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id

    /**
     * id
     */
    @ApiModelProperty("id")
    @Column(name = "id")
    private String id;

    /**
     * user_id
     */
    @ApiModelProperty("user_id")
    @Column(name = "user_id")
    private String userId;

    /**
     * f_name
     */
    @ApiModelProperty("f_name")
    @Column(name = "f_name")
    private String fName;

    /**
     * l_name
     */
    @ApiModelProperty("l_name")
    @Column(name = "l_name")
    private String lName;

    /**
     * phone
     */
    @ApiModelProperty("phone")
    @Column(name = "phone")
    private String phone;

    /**
     * birthday
     */
    @ApiModelProperty("birthday")
    @Column(name = "birthday")
    private Date birthday;

    /**
     * id_num
     */
    @ApiModelProperty("id_num")
    @Column(name = "id_num")
    private String idNum;

    /**
     * countries_id
     */
    @ApiModelProperty("countries_id")
    @Column(name = "countries_id")
    private String countriesId;

    /**
     * states_id
     */
    @ApiModelProperty("states_id")
    @Column(name = "states_id")
    private String statesId;

    /**
     * cities_id
     */
    @ApiModelProperty("cities_id")
    @Column(name = "cities_id")
    private String citiesId;

    /**
     * address
     */
    @ApiModelProperty("address")
    @Column(name = "address")
    private String address;

    /**
     * postcode
     */
    @ApiModelProperty("postcode")
    @Column(name = "postcode")
    private String postcode;

    /**
     * photo
     */
    @ApiModelProperty("photo")
    @Column(name = "photo")
    private String photo;

    /**
     * 版號
     */
    @ApiModelProperty("版號")
    @Column(name = "version")
    private Integer version;

    /**
     * 建立人
     */
    @ApiModelProperty("建立人")
    @Column(name = "create_user")
    private String createUser;

    /**
     * 建立時間
     */
    @ApiModelProperty("建立時間")
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 建立ip
     */
    @ApiModelProperty("建立ip")
    @Column(name = "create_ip")
    private String createIp;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    @Column(name = "update_user")
    private String updateUser;

    /**
     * 更新時間
     */
    @ApiModelProperty("更新時間")
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 更新ip
     */
    @ApiModelProperty("更新ip")
    @Column(name = "update_ip")
    private String updateIp;

    /**
     * 0:未刪除;1:刪除
     */
    @ApiModelProperty("0:未刪除;1:刪除")
    @Column(name = "is_del")
    private boolean isDel;

}
