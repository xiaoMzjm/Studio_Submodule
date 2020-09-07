package com.base.authority.client.client;

import java.util.*;

import com.base.authority.client.model.UserRoleDTO;

/**
 * @author:小M
 * @date:2020/8/1 1:45 AM
 */
public interface UserRoleClient {

    void bind(List<String> userCodeList, List<String> roleCodeList) throws RuntimeException;

    List<UserRoleDTO> selectByUserCode(String userCode) throws RuntimeException;

    Map<String,List<UserRoleDTO>> selectByUserCodes(List<String> userCodes) throws RuntimeException;
}
