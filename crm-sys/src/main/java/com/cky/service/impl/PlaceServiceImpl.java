package com.cky.service.impl;

import com.cky.dao.CitiesDAO;
import com.cky.dao.CountriesDAO;
import com.cky.dao.StatesDAO;
import com.cky.entity.Cities;
import com.cky.entity.Countries;
import com.cky.entity.States;
import com.cky.service.PlaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private CountriesDAO countriesDAO;

    @Autowired
    private StatesDAO statesDAO;

    @Autowired
    private CitiesDAO citiesDAO;

    @Override
    public Map<String, String> selectCountriesMap() {
        return countriesDAO.selectMap();
    }

    @Override
    public Map<String, String> selectStatesMap(String countriesId) {
        return statesDAO.selectMap(countriesId);
    }

    @Override
    public Map<String, String> selectCitiesMap(String statesId) {
        return citiesDAO.selectMap(statesId);
    }


    @Override
    public List<Countries> selectCountriesAll() {
        return countriesDAO.selectAll();
    }

    @Override
    public List<States> selectStatesByParentCode(String countriesCode) {
        Example example = new Example(States.class);
        example.createCriteria().andEqualTo("countriesCode", countriesCode);
        return statesDAO.selectByExample(example);
    }

    @Override
    public List<Cities> selectCitiesByParentCode(String countriesCode, String statesCode) {
        Example example = new Example(Cities.class);
        example.createCriteria().andEqualTo("countriesCode", countriesCode).andEqualTo("statesCode",statesCode);
        return citiesDAO.selectByExample(example);
    }
}
