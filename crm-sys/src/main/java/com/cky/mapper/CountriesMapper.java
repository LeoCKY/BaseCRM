package com.cky.mapper;


import com.cky.base.mapper.BaseMapper;
import com.cky.entity.Countries;

import java.util.Map;

public interface CountriesMapper extends BaseMapper<Countries, String> {

    Map<String,String> selectMap();

}