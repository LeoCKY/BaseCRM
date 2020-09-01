package com.cky.mapper;


import com.cky.entity.Cities;

public interface CitiesMapper {

    int insert(Cities record);

    int insertSelective(Cities record);
}