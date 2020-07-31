package com.base.authority.server.manager;

import java.util.List;

import com.base.authority.client.common.Enums.AuthorityTypeEnum;
import com.base.authority.client.model.AuthorityDTO;

/**
 * @author:小M
 * @date:2020/3/29 8:28 PM
 */
public interface AuthorityManager {

    AuthorityDTO add(String name, String code, AuthorityTypeEnum authorityTypeEnum, String fatherCode) throws Exception;

    AuthorityDTO selectByName(String name);

    AuthorityDTO selectByCode(String code);

    List<AuthorityDTO> selectAll();

    List<AuthorityDTO> selectByCodeList(List<String> codeList);

}
