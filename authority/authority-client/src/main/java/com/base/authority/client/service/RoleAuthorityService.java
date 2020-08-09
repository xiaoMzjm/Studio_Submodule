package com.base.authority.client.service;

import java.util.*;

/**
 * @author:小M
 * @date:2020/7/31 1:05 AM
 */
public interface RoleAuthorityService {

    void add(Map<String,List<String>> roleAndAuthorityListMap) throws RuntimeException;


}
