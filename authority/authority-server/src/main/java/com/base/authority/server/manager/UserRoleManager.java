package com.base.authority.server.manager;

import java.util.List;

import com.base.authority.client.model.UserRoleDTO;

/**
 * @author:小M
 * @date:2020/8/1 1:38 AM
 */
public interface UserRoleManager {

    void add(List<String> userCodeList, List<String> roleCodeList) throws Exception;

    List<UserRoleDTO> selectByUserCode(String userCode) throws Exception;
}
