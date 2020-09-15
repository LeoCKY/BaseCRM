package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.CitiesDAO;
import com.cky.entity.Cities;
import com.cky.mapper.CitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CitiesDAOImpl extends BaseDAOImpl<Cities, String> implements CitiesDAO {

    @Autowired
    private CitiesMapper citiesMapper;

    @Override
    public BaseMapper<Cities, String> getMapper() {
        return citiesMapper;
    }

    @Override
    public Map<String, String> selectMap(String statesId) {
        return citiesMapper.selectMap(statesId);
    }
}
