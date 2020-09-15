package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  tb_states
 * @author Leo Chen 2020-09-15
 */
@Entity
@Data
@Table(name="tb_states")
@ApiModel("tb_states")
public class States implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /**
     * id
     */
    @ApiModelProperty("id")
    @Column(name="id")
    private String id;

    /**
     * name
     */
    @ApiModelProperty("name")
    @Column(name="name")
    private String name;

    /**
     * countries_id
     */
    @ApiModelProperty("countries_id")
    @Column(name="countries_id")
    private String countriesId;

    /**
     * countries_code
     */
    @ApiModelProperty("countries_code")
    @Column(name="countries_code")
    private String countriesCode;

    /**
     * fips_code
     */
    @ApiModelProperty("fips_code")
    @Column(name="fips_code")
    private String fipsCode;

    /**
     * iso2
     */
    @ApiModelProperty("iso2")
    @Column(name="iso2")
    private String iso2;

    public States() {
    }

}
