package com.cky.base.user;

import lombok.Data;

import java.io.Serializable;


@Data
public class CurrentRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String description;

    public CurrentRole(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CurrentRole() {
    }
}
