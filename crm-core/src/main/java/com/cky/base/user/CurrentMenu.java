package com.cky.base.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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

    private List<String> permission;

    private Byte menuType;


    public CurrentMenu(String id, String name, String parentId, String url, Integer orderNum, String icon, List<String> permission, Byte menuType) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.url = url;
        this.orderNum = orderNum;
        this.icon = icon;
        this.permission = permission;
        this.menuType = menuType;
    }

    public CurrentMenu() {
    }
}
