package com.base.authority.server.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.base.authority.client.common.Enums.AuthorityTypeEnum;
import com.base.authority.client.model.AuthorityVO;
import com.base.authority.client.model.UserRoleDTO;
import com.base.authority.client.client.AuthorityClient;
import com.base.authority.server.manager.AuthorityManager;
import com.base.authority.server.manager.RoleAuthorityManager;
import com.base.authority.server.manager.UserRoleManager;
import com.base.authority.server.model.RoleAuthorityDO;
import com.base.authority.server.model.convertor.AuthorityConvertor;
import com.base.authority.client.model.AuthorityDTO;
import com.base.common.exception.BaseException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:小M
 * @date:2020/3/29 8:48 PM
 */
@Service
public class AuthorityClientImpl implements AuthorityClient {

    @Autowired
    private AuthorityManager authorityManager;
    @Autowired
    private RoleAuthorityManager roleAuthorityManager;
    @Autowired
    private UserRoleManager userRoleManager;

    /**
     * 获取菜单树
     * @return
     */
    @Override
    public List<AuthorityVO> listAll(){
        List<AuthorityDTO> authorityDTOList = authorityManager.selectAll();
        List<AuthorityVO> authorityVOList = AuthorityConvertor.dto2voList(authorityDTOList);
        authorityVOList = this.buildTree(authorityVOList , null);
        return authorityVOList;
    }

    @Override
    public List<AuthorityVO> listByUserCode(String userCode) throws RuntimeException{
        List<AuthorityVO> result = Lists.newArrayList();
        List<UserRoleDTO> userRoleDTOList = userRoleManager.listByUserCode(userCode);
        if(CollectionUtils.isEmpty(userRoleDTOList)) {
            return result;
        }
        List<String> roleCodeList = userRoleDTOList.stream().map(UserRoleDTO::getRoleCode).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(roleCodeList)) {
            return result;
        }
        List<RoleAuthorityDO> roleAuthorityDOList = roleAuthorityManager.listByRoleCodes(roleCodeList);
        if(CollectionUtils.isEmpty(roleAuthorityDOList)) {
            return result;
        }
        List<String> authorityCodelist = roleAuthorityDOList.stream().map(RoleAuthorityDO::getPowerCode).collect(Collectors.toList());
        List<AuthorityDTO> authorityDTOList = authorityManager.selectByCodeList(authorityCodelist);
        if(CollectionUtils.isEmpty(authorityDTOList)) {
            return result;
        }
        List<AuthorityVO> authorityVOList = AuthorityConvertor.dto2voList(authorityDTOList);
        authorityVOList = this.buildTree(authorityVOList , null);
        return authorityVOList;
    }

    @Override
    public Boolean hasAuthority(String userCode, String authorityCode) {
        List<RoleAuthorityDO> roleAuthorityDOList = roleAuthorityManager.listByAuthCodes(Lists.newArrayList(authorityCode));
        if(CollectionUtils.isEmpty(roleAuthorityDOList)) {
            return false;
        }
        List<String> roleList = roleAuthorityDOList.stream().map(RoleAuthorityDO::getRoleCode).collect(Collectors.toList());
        List<UserRoleDTO> userRoleDTOList = userRoleManager.listByUserCodeAndRoleCodes(userCode, roleList);
        if(CollectionUtils.isNotEmpty(userRoleDTOList)) {
            return true;
        }
        return false;
    }

    /**
     * 构建菜单树
     * @param companyVOList
     * @param fatherCode
     * @return
     */
    private List<AuthorityVO> buildTree(List<AuthorityVO> companyVOList, String fatherCode) {
        List<AuthorityVO> list = Lists.newArrayList();

        companyVOList.forEach(vo -> {
            if (Objects.equals(fatherCode, vo.getFatherCode())) {
                vo.setChildren(buildTree(companyVOList, vo.getCode()));
                list.add(vo);
            }
        });

        return list;
    }

    /**
     * 添加权限
     * @param name
     * @param authorityType
     * @param fatherCode
     * @throws Exception
     */
    @Override
    public AuthorityVO add(String name, String code , String authorityType, String fatherCode) throws RuntimeException{
        if (StringUtils.isEmpty(name)) {
            throw new BaseException("名称为空");
        }
        if (StringUtils.isEmpty(authorityType)) {
            throw new BaseException("类型为空");
        }
        if (StringUtils.isEmpty(code)) {
            throw new BaseException("编码为空");
        }
        AuthorityTypeEnum authorityTypeEnum = AuthorityTypeEnum.getByStr(authorityType);
        if (authorityTypeEnum == null) {
            throw new BaseException("类型错误");
        }
        AuthorityDTO authorityDTO = authorityManager.add(name, code, authorityTypeEnum, fatherCode);
        return AuthorityConvertor.dto2vo(authorityDTO);
    }

    @Override
    public List<AuthorityVO> selectAllAuthorityAndRole() throws RuntimeException {
        List<RoleAuthorityDO> roleAuthorityDOList = roleAuthorityManager.listAll();
        if(CollectionUtils.isEmpty(roleAuthorityDOList)) {
            return Lists.newArrayList();
        }
        Map<String,List<String>> powerCode2RolesMap = new HashMap<>();
        for(RoleAuthorityDO roleAuthorityDO : roleAuthorityDOList) {
            List<String> roleList = powerCode2RolesMap.get(roleAuthorityDO.getPowerCode());
            if(roleList == null) {
                roleList = Lists.newArrayList();
            }
            roleList.add(roleAuthorityDO.getRoleCode());
            powerCode2RolesMap.put(roleAuthorityDO.getPowerCode(), roleList);
        }
        List<AuthorityDTO> authorityDTOList = authorityManager.selectAll();
        List<AuthorityVO> authorityVOList = AuthorityConvertor.dto2voList(authorityDTOList);
        for(AuthorityVO authorityVO : authorityVOList) {
            List<String> roleList = powerCode2RolesMap.get(authorityVO.getCode());
            authorityVO.setRoles(roleList);
        }
        authorityVOList = this.buildTree(authorityVOList , null);
        return authorityVOList;
    }
}