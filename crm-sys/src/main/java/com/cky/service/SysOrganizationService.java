package com.cky.service;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.res.ResJSONBean;
import com.cky.entity.SysOrganization;

import java.util.List;

public interface SysOrganizationService {

    /**
     * 分頁查詢
     *
     * @param
     * @return
     */
    List<SysOrganization> selectListByPage(SysOrganization sysOrganization);


    JSONArray getOrgJsonList();

    /**
     * 新增
     *
     * @param org
     * @return
     */
    int add(SysOrganization org);

    /**
     * 更新
     *
     * @param org
     * @return
     */
    ResJSONBean updateById(SysOrganization org);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    ResJSONBean delById(String id);


    SysOrganization selectByPrimaryKey(String id);
}
