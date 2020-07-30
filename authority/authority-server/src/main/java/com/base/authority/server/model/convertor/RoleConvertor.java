package com.base.authority.server.model.convertor;

import java.util.List;

import com.base.authority.client.model.RoleDTO;
import com.base.authority.server.model.RoleDO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author:小M
 * @date:2020/7/31 12:36 AM
 */
public class RoleConvertor {

    public static RoleDTO doToDTO(RoleDO roleDO){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setCode(roleDO.getCode());
        roleDTO.setName(roleDO.getName());
        return roleDTO;
    }

    public static List<RoleDTO> doToDTOList(List<RoleDO> roleDOList){
        if(CollectionUtils.isEmpty(roleDOList)) {
            return null;
        }
        List<RoleDTO> result = Lists.newArrayList();
        for(RoleDO roleDO : roleDOList) {
            result.add(doToDTO(roleDO));
        }
        return result;
    }
}
