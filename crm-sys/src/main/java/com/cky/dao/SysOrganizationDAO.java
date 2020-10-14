package com.cky.dao;

import com.cky.base.dao.BaseDAO;
import com.cky.entity.SysOrganization;

import java.util.List;

public interface SysOrganizationDAO extends BaseDAO<SysOrganization,String> {

    List<SysOrganization> selectOrgSuperList();
}
