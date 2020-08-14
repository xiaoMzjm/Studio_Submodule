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

    RoleAuthorityDO getByRoleCodeAndAuthCode(String roleCode, String powerCode) throws RuntimeException;

    List<RoleAuthorityDO> listAll() throws RuntimeException;

    List<RoleAuthorityDO> listByRoleCodes(List<String> roleList) throws RuntimeException;

    List<RoleAuthorityDO> listByAuthCodes(List<String> authCodeList) throws RuntimeException;
}
