package com.cky.dao;

import com.cky.base.dao.BaseDAO;
import com.cky.entity.Cities;

import java.util.Map;

public interface CitiesDAO extends BaseDAO<Cities, String> {

    Map<String, String> selectMap(String statesId);

}
