package com.cky.mapper;


import com.cky.entity.Countries;

public interface CountriesMapper {

    int insert(Countries record);

    int insertSelective(Countries record);
}