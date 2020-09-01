package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  tb_sys_role_user
 * @author Leo Chen 2020-08-31
 */
@Entity
@Data
@Table(name="tb_sys_role_user")
@ApiModel("tb_sys_role_user")
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id

    /**
     * user_id
     */
    @ApiModelProperty("user_id")
    @Column(name = "user_id")
    private String userId;

    /**
     * role_id
     */
    @ApiModelProperty("role_id")
    @Column(name = "role_id")
    private String roleId;

    public SysRoleUser() {
    }

}
