package com.cky.dao.impl;

import com.cky.base.dao.impl.BaseDAOImpl;
import com.cky.base.mapper.BaseMapper;
import com.cky.dao.SysOrganizationDAO;
import com.cky.entity.SysOrganization;
import com.cky.mapper.SysOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class SysOrganizationDAOImpl extends BaseDAOImpl<SysOrganization,String> implements SysOrganizationDAO {

    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public BaseMapper<SysOrganization, String> getMapper() { return sysOrganizationMapper; }

    @Override
    public List<SysOrganization> selectOrgSuperList() {
        Example example = new Example(SysOrganization.class);
        example.createCriteria().andIsNull("parentId");
        return sysOrganizationMapper.selectByExample(example);
    }
}
