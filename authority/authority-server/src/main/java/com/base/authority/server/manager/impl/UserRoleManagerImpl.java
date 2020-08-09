package com.base.authority.server.manager.impl;

import java.util.Date;
import java.util.List;

import com.base.authority.client.model.UserRoleDTO;
import com.base.authority.server.dao.UserRoleDAO;
import com.base.authority.server.manager.UserRoleManager;
import com.base.authority.server.model.UserRoleDO;
import com.base.authority.server.model.convertor.UserRoleConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/8/1 1:40 AM
 */
@Component
public class UserRoleManagerImpl implements UserRoleManager {

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public void add(List<String> userCodeList, List<String> roleCodeList) throws RuntimeException {
        for(String userCode : userCodeList) {
            userRoleDAO.deleteByUserCode(userCode);
        }
        for(String userCode : userCodeList) {
            for(String roleCode : roleCodeList) {
                UserRoleDO userRoleDO = new UserRoleDO();
                Date now = new Date();
                userRoleDO.setGmtCreate(now);
                userRoleDO.setGmtModified(now);
                userRoleDO.setRoleCode(roleCode);
                userRoleDO.setUserCode(userCode);
                userRoleDAO.save(userRoleDO);
            }
        }
    }

    @Override
    public List<UserRoleDTO> selectByUserCode(String userCode) throws RuntimeException {
        UserRoleDO userRoleDO = new UserRoleDO();
        userRoleDO.setUserCode(userCode);
        Example<UserRoleDO> example = Example.of(userRoleDO);
        List<UserRoleDO> userRoleDOList = userRoleDAO.findAll(example);
        return UserRoleConvertor.do2dtoList(userRoleDOList);
    }

    @Override
    public List<UserRoleDTO> selectByUserCodes(List<String> userCodeList) throws RuntimeException {
        return UserRoleConvertor.do2dtoList(userRoleDAO.findByUserCodeIn(userCodeList));
    }
}
