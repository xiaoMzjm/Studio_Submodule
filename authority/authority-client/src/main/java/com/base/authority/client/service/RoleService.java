package com.base.authority.client.service;

import java.util.List;

import com.base.authority.client.model.RoleDTO;
/**
 * @author:小M
 * @date:2020/7/30 1:01 AM
 */
public interface RoleService {

    void add(String name) throws Exception;

    List<RoleDTO> selectAll() throws Exception;

    List<RoleDTO> selectByCodes(List<String> codes) throws Exception;

    void delete(String code) throws Exception;

    void updateName(String code, String name) throws Exception;


}
