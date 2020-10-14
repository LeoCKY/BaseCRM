package com.cky.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.cky.base.res.ResJSONBean;
import com.cky.dao.SysOrganizationDAO;
import com.cky.entity.SysOrganization;
import com.cky.service.SysOrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SysOrganizationServiceImpl implements SysOrganizationService {

    @Autowired
    private SysOrganizationDAO organizationDAO;

    @Override
    public List<SysOrganization> selectListByPage(SysOrganization sysOrganization) {
        return null;
    }

    @Override
    public int add(SysOrganization org) {
        return organizationDAO.insertSelective(org);
    }

    @Override
    public ResJSONBean updateById(SysOrganization org) {
        if (StringUtils.isEmpty(org.getId())) {
            return ResJSONBean.error("獲取數據失敗");
        }
        ResJSONBean j = new ResJSONBean();
        try {
            organizationDAO.updateByPrimaryKeySelective(org);
            j.setMsg("Update success.");
        } catch (Exception e) {
            log.error("SysOrganizationService updateById error : {}", e);
            j.setMsg("Update error.");
            j.setFlag(false);
        }
        return j;
    }

    @Override
    public ResJSONBean delById(String id) {
        if (StringUtils.isEmpty(id)) {
            return ResJSONBean.error("獲取數據失敗");
        }
        ResJSONBean j = new ResJSONBean();
        try {
            organizationDAO.deleteByIds(id);
            j.setMsg("Delete Success.");
        } catch (Exception e) {
            log.error("SysOrganizationService delById error : {}", e);
            j.setMsg("Delete Error.");
            j.setFlag(false);
         }
        return j;
    }

    @Override
    public JSONArray getOrgJsonList() {
        List<SysOrganization> orgAll = organizationDAO.selectAll();
        List<SysOrganization> orgRoots = orgAll.stream().filter( o -> o.getParentId() == null).collect(Collectors.toList());
        orgAll.removeAll(orgRoots);

        JSONArray jsonArr = new JSONArray();
        for (SysOrganization org : orgRoots) {
            SysOrganization child = child(org, orgAll, 0, 0);
            jsonArr.add(child);
        }

        System.out.println("Orgs Json : " + jsonArr);
        return jsonArr;
    }

    public SysOrganization child(SysOrganization sysOrg, List<SysOrganization> sysOrganizations, Integer pNum, Integer num) {
        List<SysOrganization> childSysOrg = sysOrganizations.stream().filter(s ->
                        sysOrg.getId().equals(s.getParentId())).collect(Collectors.toList());
        sysOrganizations.removeAll(childSysOrg);
        SysOrganization o;
        for (SysOrganization org : childSysOrg) {
            ++num;
            o = child(org, sysOrganizations, pNum, num);
            sysOrg.addChild(org);
        }
        return sysOrg;
    }

    @Override
    public SysOrganization selectByPrimaryKey(String id) {
        return organizationDAO.selectByPrimaryKey(id);
    }
}
