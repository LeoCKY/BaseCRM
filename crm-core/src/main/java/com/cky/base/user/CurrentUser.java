package com.cky.base.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CurrentUser implements Serializable {

    private String id;

    private String username;
}
