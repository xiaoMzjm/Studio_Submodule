package com.base.authority.client.client;

import java.util.*;

/**
 * @author:小M
 * @date:2020/7/31 1:05 AM
 */
public interface RoleAuthorityClient {

    void add(Map<String,List<String>> roleAndAuthorityListMap) throws RuntimeException;


}
