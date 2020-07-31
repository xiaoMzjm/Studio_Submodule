package com.base.authority.server.model.convertor;

import java.beans.Beans;
import java.util.List;

import com.base.authority.client.model.UserRoleDTO;
import com.base.authority.server.model.UserRoleDO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

/**
 * @author:小M
 * @date:2020/8/1 1:59 AM
 */
public class UserRoleConvertor {

    public static UserRoleDTO do2dto(UserRoleDO userRoleDO) {
        if(userRoleDO == null) {
            return null;
        }
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        BeanUtils.copyProperties(userRoleDO, userRoleDTO);
        return userRoleDTO;
    }

    public static List<UserRoleDTO> do2dtoList(List<UserRoleDO> userRoleDOList) {
        List<UserRoleDTO> result = Lists.newArrayList();
        if(CollectionUtils.isEmpty(userRoleDOList)) {
            return result;
        }
        for(UserRoleDO userRoleDO : userRoleDOList) {
            result.add(do2dto(userRoleDO));
        }
        return result;
    }
}
