package com.cky.base.dao.impl;


import com.cky.base.dao.BaseDAO;
import com.cky.base.mapper.BaseMapper;
import com.cky.base.res.ReType;
import com.cky.base.user.CurrentUser;
import com.cky.util.CurrentUtil;
import com.cky.util.DateUtil;
import com.cky.util.IpUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Slf4j
public abstract class BaseDAOImpl<T, E extends Serializable> implements BaseDAO<T, E> {

    /**
     * general field(通用欄位)
     */
    private static final String CREATE_USER = "create_user";

    private static final String CREATE_DATE = "create_date";

    private static final String CREATE_IP = "create_ip";

    private static final String UPDATE_USER = "updateUser";

    private static final String UPDATE_DATE = "updateDate";

    private static final String UPDATE_IP = "updateIp";

    private static final String VERSION = "version";

    //系統默認 id 如果主鍵為其他欄位 則需要自己手動 生成 寫入 id
    private static final String ID = "id";

    private static final String STR = "java.lang.String";


    public abstract BaseMapper<T, E> getMapper();

    @Override
    public List<T> select(T t) {
        return getMapper().select(t);
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    @Override
    public List<T> selectByIds(String ids) {
        return getMapper().selectByIds(ids);
    }

    @Override
    public int selectCount(T t) {
        return getMapper().selectCount(t);
    }


    @Override
    public int deleteByPrimaryKey(E id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        try {
            record = addValue(record, true);
        } catch (Exception e) {

        }
        return getMapper().insert(record);
    }

    /**
     * 通用註入創建 更新信息 可通過super調用
     *
     * @param record
     * @param flag
     * @return
     */
    public T addValue(T record, boolean flag) {
        CurrentUser currentUser = CurrentUtil.getUser();
        //統一處理公共欄位
        Class<?> clazz = record.getClass();
        String operator, operateDate,operateIP;
        try {
            if (flag) {
                //添加 id uuid支持
                Field idField = clazz.getDeclaredField(ID);
                idField.setAccessible(true);
                Object o = idField.get(record);
                Class<?> type = idField.getType();
                String name = type.getName();
                if ((o == null) && STR.equals(name)) {
                    //已經有值的情況下 不覆蓋
                    idField.set(record, UUID.randomUUID().toString().replace("-", "").toLowerCase());
                }
                operator = CREATE_USER;
                operateDate = CREATE_DATE;
                operateIP = CREATE_IP;
            } else {
                operator = UPDATE_USER;
                operateDate = UPDATE_DATE;
                operateIP = UPDATE_IP;
            }
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                            .getRequest();
            Field field = clazz.getDeclaredField(operator);
            field.setAccessible(true);
            field.set(record, currentUser.getId());
            Field fieldDate = clazz.getDeclaredField(operateDate);
            fieldDate.setAccessible(true);
            fieldDate.set(record, DateUtil.getCurrentTime());
            Field fieldIP = clazz.getDeclaredField(operateIP);
            fieldIP.setAccessible(true);
            fieldIP.set(record, IpUtil.getIp(request));
        } catch (NoSuchFieldException e) {
            //無此欄位
            log.error("BaseDAOImpl error : {}", e);
        } catch (IllegalAccessException e) {
            log.error("BaseDAOImpl error : {}", e);
        }
        return record;
    }

    @Override
    public int insertSelective(T record) {
        try {
            record = addValue(record, true);
        } catch (Exception e) {
        }
        return getMapper().insertSelective(record);
    }


    @Override
    public int updateByPrimaryKeySelective(T record) {
        try {
            record = addValue(record, false);
        } catch (Exception e) {

        }
        return getMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        try {
            record = addValue(record, false);
        } catch (Exception e) {

        }
        return getMapper().updateByPrimaryKey(record);
    }

    @Override
    public List<T> selectListByPage(T record) {
        return getMapper().selectListByPage(record);
    }

    @Override
    public int deleteByPrimaryKey(Object o) {
        return getMapper().deleteByPrimaryKey(o);
    }

    @Override
    public int delete(T t) {
        return getMapper().delete(t);
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return getMapper().existsWithPrimaryKey(o);
    }

    @Override
    public T selectByPrimaryKey(Object o) {
        return getMapper().selectByPrimaryKey(o);
    }

    @Override
    public T selectOne(T t) {
        return getMapper().selectOne(t);
    }

    @Override
    public int deleteByIds(String s) {
        return getMapper().deleteByIds(s);
    }

    @Override
    public int deleteByExample(Object o) {
        return getMapper().deleteByExample(o);
    }


    @Override
    public List<T> selectByExample(Object o) {
        List<T> list =null;
        try {
            list = getMapper().selectByExample(o);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return list;
    }


    @Override
    public int selectCountByExample(Object o) {
        return getMapper().selectCountByExample(o);
    }


    @Override
    public T selectOneByExample(Object o) {
        return getMapper().selectOneByExample(o);
    }


    @Override
    public int updateByExample(T t, Object o) {
        return getMapper().updateByExample(t, o);
    }


    @Override
    public int updateByExampleSelective(T t, Object o) {
        return getMapper().updateByExampleSelective(t, o);
    }


    @Override
    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return getMapper().selectByExampleAndRowBounds(o, rowBounds);
    }


    @Override
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return getMapper().selectByRowBounds(t, rowBounds);
    }


    @Override
    public int insertList(List<T> list) {
//        return getMapper().insertList(list);
        return 0;
    }

    @Override
    public int insertUseGeneratedKeys(T t) {
//        return getMapper().insertUseGeneratedKeys(t);
        return 0;
    }

    @Override
    public List<T> showAll(T t) {
        List<T> tList = null;
        try {
            tList = getMapper().selectListByPage(t);
        } catch (Exception e) {
            log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
         return tList;
    }

    /**
     * 公共展示类
     *
     * @param t     实体
     * @param page  页
     * @param limit 行
     * @return
     */
    @Override
    public ReType show(T t, int page, int limit) {
        List<T> tList = null;
        Page<T> tPage = PageHelper.startPage(page, limit);
        try {
            tList = getMapper().selectListByPage(t);
        } catch (Exception e) {
            log.error("class:BaseServiceImpl ->method:show->message:" + e.getMessage());
            e.printStackTrace();
        }
        return new ReType(tPage.getTotal(), tList);
    }
}
