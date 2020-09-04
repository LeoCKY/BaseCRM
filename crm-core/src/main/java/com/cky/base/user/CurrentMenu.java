package com.cky.base.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@ToString
public class CurrentMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String parentId;

    private String url;

    private Integer orderNum;

    private String icon;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private String permission;

    private Byte menuType;
    /**
     * 菜单排序id 填充菜单展示id
     */
    private int num;


    public CurrentMenu(String id, String name, String parentId, String url, Integer orderNum, String icon, String permission, Byte menuType, int num) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.url = url;
        this.orderNum = orderNum;
        this.icon = icon;
        this.permission = permission;
        this.menuType = menuType;
        this.num = num;
    }

    public CurrentMenu() {
    }
}
