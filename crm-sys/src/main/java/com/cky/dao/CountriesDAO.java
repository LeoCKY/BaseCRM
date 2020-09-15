package com.cky.dao;

import com.cky.base.dao.BaseDAO;
import com.cky.entity.Countries;

import java.util.Map;

public interface CountriesDAO extends BaseDAO<Countries, String> {

    Map<String, String> selectMap();

}
