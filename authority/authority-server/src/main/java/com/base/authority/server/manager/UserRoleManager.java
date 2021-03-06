package com.base.authority.server.manager;

import java.util.List;

import com.base.authority.client.model.UserRoleDTO;

/**
 * @author:小M
 * @date:2020/8/1 1:38 AM
 */
public interface UserRoleManager {

    void add(List<String> userCodeList, List<String> roleCodeList) throws RuntimeException;

    List<UserRoleDTO> listByUserCode(String userCode) throws RuntimeException;

    List<UserRoleDTO> listByUserCodes(List<String> userCodeList) throws RuntimeException;

    List<UserRoleDTO> listByUserCodeAndRoleCodes(String userCode, List<String> roleCodes) throws RuntimeException;

}
