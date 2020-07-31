package com.base.authority.server.service;

import java.util.List;

import com.base.authority.client.model.RoleDTO;
import com.base.authority.client.service.RoleService;
import com.base.authority.server.manager.RoleManager;
import com.base.authority.server.model.convertor.RoleConvertor;
import com.base.authority.server.model.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:小M
 * @date:2020/7/30 1:03 AM
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleManager roleManager;

    @Override
    public void add(String name) throws Exception {

        roleManager.add(name);
    }

    @Override
    public List<RoleDTO> selectAll() throws Exception {
        return roleManager.listAll();
    }

    @Override
    public List<RoleDTO> selectByCodes(List<String> codes) throws Exception {
        return roleManager.selectByCodes(codes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String code) throws Exception {
        // 删除角色
        roleManager.delete(code);
        // 删除角色和权限的绑定关系 TODO

        // 删除角色和用户的绑定关系 TODO
    }

    @Override
    public void updateName(String code, String name) throws Exception {
        roleManager.updateName(code, name);
    }
}
