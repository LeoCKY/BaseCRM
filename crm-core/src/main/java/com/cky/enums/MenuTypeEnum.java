package com.cky.enums;

public enum MenuTypeEnum {

    LEVEL1_MENU(2,"level one menu"),
    LEVEL2_MENU(0,"level two menu"),
    BUTTON(1, "button");

    int type;

    String name;

    MenuTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
