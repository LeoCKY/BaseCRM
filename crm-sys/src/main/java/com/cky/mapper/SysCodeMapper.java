package com.cky.mapper;


import com.cky.entity.SysCode;

public interface SysCodeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    SysCode selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysCode record);

    int updateByPrimaryKey(SysCode record);
}