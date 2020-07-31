package com.base.authority.server.service;

import java.util.List;

import com.base.authority.client.model.UserRoleDTO;
import com.base.authority.client.service.UserRoleService;
import com.base.authority.server.manager.UserRoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/8/1 1:46 AM
 */
@Component
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleManager userRoleManager;

    @Override
    public void bind(List<String> userCodeList, List<String> roleCodeList) throws Exception {
        userRoleManager.add(userCodeList, roleCodeList);
    }

    @Override
    public List<UserRoleDTO> selectByUserCode(String userCode) throws Exception {
        return userRoleManager.selectByUserCode(userCode);
    }

}
