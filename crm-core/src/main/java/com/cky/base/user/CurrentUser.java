package com.cky.base.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class CurrentUser implements Serializable {

    private String id;

    private String username;

    private String email;

    private String photo;

    private String fName;

    private String lName;

    private List<CurrentRole> currentRoleList;

    private List<CurrentMenu> currentMenuList;

    public CurrentUser() {
    }

    public CurrentUser(String id, String username, String email, String photo, String fName, String lName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.fName = fName;
        this.lName = lName;
    }
}
