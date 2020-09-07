package com.base.authority.server.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.base.authority.client.model.UserRoleDTO;
import com.base.authority.client.client.UserRoleClient;
import com.base.authority.server.manager.UserRoleManager;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:小M
 * @date:2020/8/1 1:46 AM
 */
@Component
public class UserRoleClientImpl implements UserRoleClient {

    @Autowired
    private UserRoleManager userRoleManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void bind(List<String> userCodeList, List<String> roleCodeList) throws RuntimeException {
        userRoleManager.add(userCodeList, roleCodeList);
    }

    @Override
    public List<UserRoleDTO> selectByUserCode(String userCode) throws RuntimeException {
        return userRoleManager.listByUserCode(userCode);
    }

    @Override
    public Map<String, List<UserRoleDTO>> selectByUserCodes(List<String> userCodes) throws RuntimeException {
        List<UserRoleDTO> userRoleDTOList = userRoleManager.listByUserCodes(userCodes);
        Map<String, List<UserRoleDTO>> result = new HashMap<>();
        if(CollectionUtils.isEmpty(userRoleDTOList)) {
            return result;
        }
        for(UserRoleDTO userRoleDTO : userRoleDTOList) {
            String userCode = userRoleDTO.getUserCode();
            List<UserRoleDTO> value = result.get(userCode);
            if(CollectionUtils.isEmpty(value)) {
                value = Lists.newArrayList();
            }
            value.add(userRoleDTO);
            result.put(userCode, value);
        }
        return result;
    }

}
