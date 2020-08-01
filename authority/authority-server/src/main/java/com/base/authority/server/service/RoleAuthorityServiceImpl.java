package com.base.authority.server.service;

import java.util.*;

import com.base.authority.client.model.AuthorityDTO;
import com.base.authority.client.service.RoleAuthorityService;
import com.base.authority.server.manager.AuthorityManager;
import com.base.authority.server.manager.RoleAuthorityManager;
import com.base.authority.server.model.RoleAuthorityDO;
import com.google.common.collect.Lists;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:小M
 * @date:2020/7/31 1:06 AM
 */
@Component
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private RoleAuthorityManager roleAuthorityManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Map<String,List<String>> roleAndAuthorityListMap) throws Exception {
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
