package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.Cities;

import java.util.Map;

public interface CitiesMapper extends BaseMapper<Cities, String> {

    Map<String, String> selectMap(String statesId);
}