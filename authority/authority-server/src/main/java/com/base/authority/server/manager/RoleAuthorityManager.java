package com.base.authority.server.manager;

import java.util.List;

import com.base.authority.server.model.RoleAuthorityDO;

/**
 * @author:小M
 * @date:2020/7/31 12:51 AM
 */
public interface RoleAuthorityManager {

    void add(String roleCode, String powerCode) throws RuntimeException;

    void deleteAll() throws RuntimeException;

    RoleAuthorityDO selectByCode(String roleCode, String powerCode) throws RuntimeException;

    List<RoleAuthorityDO> selectAll() throws RuntimeException;

    List<RoleAuthorityDO> selectByRoleList(List<String> roleList) throws RuntimeException;
}
