package com.cky.service;

import com.cky.entity.Cities;
import com.cky.entity.Countries;
import com.cky.entity.States;

import java.util.List;
import java.util.Map;

public interface PlaceService {

    Map<String, String> selectCountriesMap();

    Map<String, String> selectStatesMap(String countriesId);

    Map<String, String> selectCitiesMap(String statesId);

    List<Countries> selectCountriesAll();

    List<States> selectStatesByParentId(String countriesCode);

    List<Cities> selectCitiesByParentId(String countriesCode, String statesCode);
}
