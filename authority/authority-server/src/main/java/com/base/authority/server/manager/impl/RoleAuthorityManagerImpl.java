package com.base.authority.server.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.base.authority.server.dao.RoleAuthorityDAO;
import com.base.authority.server.manager.RoleAuthorityManager;
import com.base.authority.server.model.RoleAuthorityDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/7/31 12:51 AM
 */
@Component
public class RoleAuthorityManagerImpl implements RoleAuthorityManager {

    @Autowired
    private RoleAuthorityDAO roleAuthorityDAO;

    @Override
    public void add(String roleCode, String powerCode) throws Exception {

        RoleAuthorityDO selectResult = selectByCode(roleCode,powerCode);
        if(selectResult != null) {
            return;
        }

        RoleAuthorityDO roleAuthorityDO = new RoleAuthorityDO();
        Date now = new Date();
        roleAuthorityDO.setGmtCreate(now);
        roleAuthorityDO.setGmtModified(now);
        roleAuthorityDO.setRoleCode(roleCode);
        roleAuthorityDO.setPowerCode(powerCode);
        roleAuthorityDAO.save(roleAuthorityDO);
    }

    @Override
    public void deleteAll() throws Exception {
        roleAuthorityDAO.deleteAll();
    }

    @Override
    public RoleAuthorityDO selectByCode(String roleCode, String powerCode) throws Exception {
        RoleAuthorityDO roleAuthorityDO = new RoleAuthorityDO();
        roleAuthorityDO.setRoleCode(roleCode);
        roleAuthorityDO.setPowerCode(powerCode);
        Example<RoleAuthorityDO> example = Example.of(roleAuthorityDO);
        Optional<RoleAuthorityDO> optional = roleAuthorityDAO.findOne(example);
        if(!optional.isPresent()) {
            return null;
        }
        return optional.get();
    }

    @Override
    public List<RoleAuthorityDO> selectAll() throws Exception {
        List<RoleAuthorityDO> result = roleAuthorityDAO.findAll();
        return result;
    }

    @Override
    public List<RoleAuthorityDO> selectByRoleList(List<String> roleList) throws Exception {
        return roleAuthorityDAO.findByRoleCodeIn(roleList);
    }

}
