package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.States;

import java.util.Map;

public interface StatesMapper extends BaseMapper<States, String> {

    Map<String, String> selectMap(String countriesId);
}