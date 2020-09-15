package com.cky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * tb_cities
 *
 * @author Leo Chen 2020-09-15
 */
@Entity
@Data
@Table(name = "tb_cities")
@ApiModel("tb_cities")
public class Cities implements Serializable {

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
     * states_id
     */
    @ApiModelProperty("states_id")
    @Column(name = "states_id")
    private String statesId;

    /**
     * states_code
     */
    @ApiModelProperty("states_code")
    @Column(name = "states_code")
    private String statesCode;

    /**
     * countries_id
     */
    @ApiModelProperty("countries_id")
    @Column(name = "countries_id")
    private String countriesId;

    /**
     * countries_code
     */
    @ApiModelProperty("countries_code")
    @Column(name = "countries_code")
    private String countriesCode;

    /**
     * latitude
     */
    @ApiModelProperty("latitude")
    @Column(name = "latitude")
    private BigDecimal latitude;

    /**
     * longitude
     */
    @ApiModelProperty("longitude")
    @Column(name = "longitude")
    private BigDecimal longitude;

    public Cities() {
    }

}
