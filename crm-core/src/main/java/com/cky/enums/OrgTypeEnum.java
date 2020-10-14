package com.cky.enums;

public enum OrgTypeEnum {

    HEAD_OFFICE(1, "head Office"),
    BRANCH_OFFICE(2, "branch office"),
    DEPARTMENT(3, "department");

    int type;
    String name;

    OrgTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
