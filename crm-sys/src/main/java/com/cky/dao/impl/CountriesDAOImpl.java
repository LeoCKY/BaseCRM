package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.CountriesDAO;
import com.cky.entity.Countries;
import com.cky.mapper.CountriesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class CountriesDAOImpl  extends BaseDAOImpl<Countries, String> implements CountriesDAO {

    @Autowired
    private CountriesMapper countriesMapper;

    @Override
    public BaseMapper<Countries, String> getMapper() {
        return countriesMapper;
    }

    @Override
    public Map<String, String> selectMap() {
        return countriesMapper.selectMap();
    }
}
