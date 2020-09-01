package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  tb_sys_menu
 * @author Leo Chen 2020-08-28
 */
@Entity
@Data
@Table(name="tb_sys_menu")
@ApiModel("tb_sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    @Id
    @ApiModelProperty("id")
    @Column(name = "id")
    private String id;

    /**
     * name
     */
    @ApiModelProperty("name")
    @Column(name = "name")
    private String name;

    /**
     * parent_id
     */
    @ApiModelProperty("parent_id")
    @Column(name = "parent_id")
    private String parentId;

    /**
     * url
     */
    @ApiModelProperty("url")
    @Column(name = "url")
    private String url;

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 圖標
     */
    @ApiModelProperty("圖標")
    @Column(name = "icon")
    private String icon;

    /**
     * 權限
     */
    @ApiModelProperty("權限")
    @Column(name = "permission")
    private String permission;

    /**
     * 1欄目 2菜单
     */
    @ApiModelProperty("1欄目 2菜单")
    @Column(name = "menu_type")
    private Byte menuType;

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

    private int num;

    private List<SysRole> roleList;

    private List<SysMenu> children = new ArrayList<SysMenu>();

    public void addChild(SysMenu sysMenu) {
        children.add(sysMenu);
    }

}
