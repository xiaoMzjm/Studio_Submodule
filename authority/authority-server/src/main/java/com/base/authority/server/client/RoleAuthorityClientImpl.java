package com.base.authority.server.client;

import java.util.*;

import com.base.authority.client.client.RoleAuthorityClient;
import com.base.authority.server.manager.RoleAuthorityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:小M
 * @date:2020/7/31 1:06 AM
 */
@Component
public class RoleAuthorityClientImpl implements RoleAuthorityClient {

    @Autowired
    private RoleAuthorityManager roleAuthorityManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Map<String,List<String>> roleAndAuthorityListMap) throws RuntimeException {
        roleAuthorityManager.deleteAll();
        ;
        for(Map.Entry<String,List<String>> entry : roleAndAuthorityListMap.entrySet()) {
            String roleCode = entry.getKey();
            for(String power : entry.getValue()) {
                roleAuthorityManager.add(roleCode,power);
            }
        }
    }


}
