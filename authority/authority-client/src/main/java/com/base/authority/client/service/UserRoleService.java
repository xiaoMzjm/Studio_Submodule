package com.base.authority.client.service;

import java.util.*;

import com.base.authority.client.model.UserRoleDTO;

/**
 * @author:小M
 * @date:2020/8/1 1:45 AM
 */
public interface UserRoleService {

    void bind(List<String> userCodeList, List<String> roleCodeList) throws Exception;

    List<UserRoleDTO> selectByUserCode(String userCode) throws Exception;

    Map<String,List<UserRoleDTO>> selectByUserCodes(List<String> userCodes) throws Exception;
}
