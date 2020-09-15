package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * tb_sys_user
 *
 * @author Leo Chen 2020-08-28
 */
@Entity
@Data
@Table(name = "tb_sys_user")
@ApiModel("tb_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @ApiModelProperty("id")
    @Column(name = "id")
    private String id;

    /**
     * password
     */
    @ApiModelProperty("account")
    @Column(name = "account")
    private String account;

    /**
     * password
     */
    @ApiModelProperty("password")
    @Column(name = "password")
    private String password;

    /**
     * 加密(鹽)
     */
    @ApiModelProperty("加密(鹽)")
    @Column(name = "salt")
    private String salt;

    /**
     * email
     */
    @ApiModelProperty("email")
    @Column(name = "email")
    private String email;

    /**
     * 狀態
     */
    @ApiModelProperty("狀態")
    @Column(name = "status")
    private Integer status;

    /**
     * 前/後台
     */
    @ApiModelProperty("前/後台")
    @Column(name = "website_type")
    private Integer websiteType;

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
    private Boolean isDel;

    private SysUserInfo userInfo;
}
