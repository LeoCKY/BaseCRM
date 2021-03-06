package com.cky.base.dao;

import com.cky.base.res.ReType;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T, E extends Serializable> {

    public List<T> select(T t);

    public List<T> selectAll();

    public List<T> selectByIds(String ids);

    public int selectCount(T t);

    public int deleteByPrimaryKey(E id);

    public int insert(T record);

    public int insertSelective(T record);

    public int updateByPrimaryKeySelective(T record);

    public int updateByPrimaryKey(T record);

    public List<T> selectListByPage(T record);

    public int deleteByPrimaryKey(Object o);

    public int delete(T t);

    public boolean existsWithPrimaryKey(Object o);

    public T selectByPrimaryKey(Object o);

    public T selectOne(T t);

    public int deleteByIds(String s);

    public int insertList(List<T> list);

    public int insertUseGeneratedKeys(T t);

    public int deleteByExample(Object o);

    public List<T> selectByExample(Object o);

    public int selectCountByExample(Object o);

    public T selectOneByExample(Object o);

    public int updateByExample(T t, Object o);

    public int updateByExampleSelective(T t, Object o);

    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    public List<T> selectByRowBounds(T t, RowBounds rowBounds);

    public List<T> showAll(T t);

    ReType show(T t, int page, int limit);
}
