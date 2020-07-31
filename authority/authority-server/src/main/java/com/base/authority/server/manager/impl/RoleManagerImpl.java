package com.base.authority.server.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.base.authority.client.model.RoleDTO;
import com.base.authority.server.dao.RoleDao;
import com.base.authority.server.manager.RoleManager;
import com.base.authority.server.model.RoleDO;
import com.base.authority.server.model.convertor.RoleConvertor;
import com.base.common.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/7/30 1:07 AM
 */
@Component
public class RoleManagerImpl implements RoleManager {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void add(String name) throws Exception {
        if(StringUtils.isBlank(name)) {
            throw new RuntimeException("角色名字为空");
        }
        RoleDTO findByName = findByName(name);
        if(findByName != null) {
            throw new RuntimeException("该角色已存在");
        }
        RoleDO roleDO = new RoleDO();
        Date now = new Date();
        roleDO.setGmtCreate(now);
        roleDO.setGmtCreate(now);
        roleDO.setCode(UUIDUtil.get());
        roleDO.setName(name);
        roleDao.save(roleDO);
    }

    @Override
    public RoleDTO findByName(String name) throws Exception {
        RoleDO roleDO = new RoleDO();
        roleDO.setName(name);
        Example example = Example.of(roleDO);
        Optional<RoleDO> optional = roleDao.findOne(example);
        if(!optional.isPresent()) {
            return null;
        }
        return RoleConvertor.doToDTO(optional.get());
    }

    @Override
    public List<RoleDTO> listAll() throws Exception {
        return RoleConvertor.doToDTOList(roleDao.findAll());
    }

    @Override
    public List<RoleDTO> selectByCodes(List<String> codes) throws Exception {
        List<RoleDO> roleDOList = roleDao.findByCodeIn(codes);
        return RoleConvertor.doToDTOList(roleDOList);
    }

    @Override
    public void delete(String code) throws Exception {
        RoleDO findByCode = findByCode(code);
        if(findByCode == null) {
            throw new RuntimeException("该角色不存在");
        }
        roleDao.delete(findByCode);
    }

    @Override
    public RoleDO findByCode(String code) throws Exception {
        RoleDO roleDO = new RoleDO();
        roleDO.setCode(code);
        Example example = Example.of(roleDO);
        Optional<RoleDO> optional = roleDao.findOne(example);
        if(!optional.isPresent()) {
            return null;
        }
        return optional.get();
    }

    @Override
    public void updateName(String code, String name) throws Exception {
        RoleDO findByCode = findByCode(code);
        if(findByCode == null) {
            throw new RuntimeException("该角色不存在");
        }
        findByCode.setName(name);
        roleDao.save(findByCode);
    }
}
