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
    public void add(String roleCode, String powerCode) throws RuntimeException {

        RoleAuthorityDO selectResult = getByRoleCodeAndAuthCode(roleCode,powerCode);
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
    public void deleteAll() throws RuntimeException {
        roleAuthorityDAO.deleteAll();
    }

    @Override
    public RoleAuthorityDO getByRoleCodeAndAuthCode(String roleCode, String powerCode) throws RuntimeException {
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
    public List<RoleAuthorityDO> listAll() throws RuntimeException {
        List<RoleAuthorityDO> result = roleAuthorityDAO.findAll();
        return result;
    }

    @Override
    public List<RoleAuthorityDO> listByRoleCodes(List<String> roleList) throws RuntimeException {
        return roleAuthorityDAO.findByRoleCodeIn(roleList);
    }

    @Override
    public List<RoleAuthorityDO> listByAuthCodes(List<String> authCodeList) throws RuntimeException {
        return roleAuthorityDAO.findByRoleCodeIn(authCodeList);
    }

}
