package com.base.authority.client.service;

import java.util.List;

import com.base.authority.client.model.RoleDTO;
/**
 * @author:小M
 * @date:2020/7/30 1:01 AM
 */
public interface RoleService {

    void add(String name) throws RuntimeException;

    List<RoleDTO> selectAll() throws RuntimeException;

    List<RoleDTO> selectByCodes(List<String> codes) throws RuntimeException;

    void delete(String code) throws RuntimeException;

    void updateName(String code, String name) throws RuntimeException;


}
