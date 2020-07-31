package com.base.authority.server.manager;

import java.util.List;

import com.base.authority.server.model.RoleAuthorityDO;

/**
 * @author:小M
 * @date:2020/7/31 12:51 AM
 */
public interface RoleAuthorityManager {

    void add(String roleCode, String powerCode) throws Exception;

    void deleteAll() throws Exception;

    RoleAuthorityDO selectByCode(String roleCode, String powerCode) throws Exception;

    List<RoleAuthorityDO> selectAll() throws Exception;

    List<RoleAuthorityDO> selectByRoleList(List<String> roleList) throws Exception;
}
