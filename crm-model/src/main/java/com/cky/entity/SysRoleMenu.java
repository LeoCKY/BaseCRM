package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  tb_sys_role_menu
 * @author Leo Chen 2020-08-28
 */
@Entity
@Data
@Table(name="tb_sys_role_menu")
@ApiModel("tb_sys_role_menu")
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /**
     * role_id
     */
    @ApiModelProperty("role_id")
    @Column(name = "role_id")
    private String roleId;

    /**
     * menu_id
     */
    @ApiModelProperty("menu_id")
    @Column(name = "menu_id")
    private String menuId;

}
