package com.base.authority.client.service;

import java.util.List;

/**
 * @author:小M
 * @date:2020/7/31 1:05 AM
 */
public interface RoleAuthorityService {

    void add(List<String> authorityList, List<String> powerList) throws Exception;


}
