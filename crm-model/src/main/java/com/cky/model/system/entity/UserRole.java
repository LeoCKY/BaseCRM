package com.cky.model.system.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * tb_user_role
 *
 * @author Leo Chen 2020-07-31
 */
@Data
@Table(name = "tb_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private String userId;

    /**
     * role_id
     */
    @Column(name = "role_id")
    private String roleId;
}
