package com.base.authority.server.manager;

import java.util.List;

import com.base.authority.client.model.RoleDTO;
import com.base.authority.server.model.RoleDO;

/**
 * @author:小M
 * @date:2020/7/30 1:05 AM
 */
public interface RoleManager {

    void add(String name) throws RuntimeException;

    RoleDTO findByName(String name) throws RuntimeException;

    List<RoleDTO> listAll() throws RuntimeException;

    List<RoleDTO> selectByCodes(List<String> codes) throws RuntimeException;

    void delete(String code) throws RuntimeException;

    RoleDO findByCode(String code) throws RuntimeException;

    void updateName(String code, String name) throws RuntimeException;


}
