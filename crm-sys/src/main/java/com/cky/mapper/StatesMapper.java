package com.cky.mapper;


import com.cky.entity.States;

public interface StatesMapper {

    int insert(States record);

    int insertSelective(States record);
}