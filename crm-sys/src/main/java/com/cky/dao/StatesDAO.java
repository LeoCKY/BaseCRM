package com.cky.dao;

import com.cky.base.dao.BaseDAO;
import com.cky.entity.States;

import java.util.Map;

public interface StatesDAO extends BaseDAO<States, String> {

    Map<String, String> selectMap(String countriesId);

}
