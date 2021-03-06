package com.base.authority.client.client;

import java.util.List;

import com.base.authority.client.model.AuthorityVO;

/**
 * @author:小M
 * @date:2020/3/29 7:48 PM
 */
public interface AuthorityClient {

    List<AuthorityVO> listAll();

    AuthorityVO add(String name, String code , String authorityType, String fatherCode) throws RuntimeException;

    List<AuthorityVO> selectAllAuthorityAndRole() throws RuntimeException;

    List<AuthorityVO> listByUserCode(String userCode) throws RuntimeException;

    Boolean hasAuthority(String userCode,  String authorityCode);
    
}
