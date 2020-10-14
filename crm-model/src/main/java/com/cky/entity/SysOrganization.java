package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * tb_sys_organization
 *
 * @author Leo Chen 2020-09-24
 */
@Entity
@Data
@Table(name = "tb_sys_organization")
@ApiModel("tb_sys_organization")
public class SysOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @ApiModelProperty("id")
    @Column(name = "id")
    private String id;

    /**
     * parent_id
     */
    @ApiModelProperty("parent_id")
    @Column(name = "parent_id")
    private String parentId;

    /**
     * name
     */
    @ApiModelProperty("name")
    @Column(name = "name")
    private String name;

    /**
     * type
     */
    @ApiModelProperty("type")
    @Column(name = "type")
    private Integer type;

    /**
     * description
     */
    @ApiModelProperty("description")
    @Column(name = "description")
    private String description;

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

    private List<SysOrganization> children = new ArrayList<>();

    public void addChild(SysOrganization sysOrg) {
        children.add(sysOrg);
    }

    public SysOrganization() {
    }

}
