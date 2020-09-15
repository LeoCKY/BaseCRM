package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.StatesDAO;
import com.cky.entity.States;
import com.cky.mapper.StatesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class StatesDAOImpl extends BaseDAOImpl<States, String> implements StatesDAO {

    @Autowired
    private StatesMapper statesMapper;

    @Override
    public BaseMapper<States, String> getMapper() {
        return statesMapper;
    }

    @Override
    public Map<String, String> selectMap(String countriesId) {
        return statesMapper.selectMap(countriesId);
    }
}
