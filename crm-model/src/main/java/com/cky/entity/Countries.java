package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tb_countries
 *
 * @author Leo Chen 2020-09-15
 */

@Entity
@Data
@Table(name = "tb_countries")
@ApiModel("Countries")
public class Countries implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /**
     * id
     */
    @ApiModelProperty("id")
    @Column(name = "id")
    private String id;

    /**
     * name
     */
    @ApiModelProperty("name")
    @Column(name = "name")
    private String name;

    /**
     * iso3
     */
    @ApiModelProperty("iso3")
    @Column(name = "iso3")
    private String iso3;

    /**
     * iso2
     */
    @ApiModelProperty("iso2")
    @Column(name = "iso2")
    private String iso2;

    /**
     * phone_code
     */
    @ApiModelProperty("phone_code")
    @Column(name = "phone_code")
    private String phoneCode;

    /**
     * capital
     */
    @ApiModelProperty("capital")
    @Column(name = "capital")
    private String capital;

    /**
     * currency
     */
    @ApiModelProperty("currency")
    @Column(name = "currency")
    private String currency;


}
