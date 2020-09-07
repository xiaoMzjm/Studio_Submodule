package com.base.user.server.manager.impl;

import java.util.Date;
import java.util.Optional;

import com.base.common.exception.BaseException;
import com.base.user.server.manager.UserManager;
import com.base.user.server.model.UserConvertor;
import com.base.user.server.model.UserDO;
import com.base.user.server.model.UserDTO;
import com.base.user.server.dao.UserDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/9/8 1:43 AM
 */
@Component
public class UserManagerImpl implements UserManager {

    @Autowired
    public UserDAO userDAO;

    /**
     * 根据code查询
     * @param code
     * @return
     */
    public UserDTO findByCode(String code) {
        UserDO wxUserInfoDO = new UserDO();
        wxUserInfoDO.setCode(code);
        Example<UserDO> example = Example.of(wxUserInfoDO);
        Optional<UserDO> findResult = userDAO.findOne(example);
        if(findResult.isPresent()) {
            return UserConvertor.do2DTO(findResult.get());
        }
        return null;
    }

    /**
     * 根据token查询
     * @param token
     * @return
     */
    public UserDTO findByToken(String token) {
        UserDO wxUserInfoDO = new UserDO();
        wxUserInfoDO.setToken(token);
        Example<UserDO> example = Example.of(wxUserInfoDO);
        Optional<UserDO> findResult = userDAO.findOne(example);
        if(findResult.isPresent()) {
            return UserConvertor.do2DTO(findResult.get());
        }
        return null;
    }

    /**
     * 保存
     */
    public UserDTO save(String code, String token){
        UserDO userDO = new UserDO();
        userDO.setCode(code);
        Date now = new Date();
        userDO.setGmtCreate(now);
        userDO.setGmtModified(now);
        userDO.setToken(token);
        userDO = userDAO.save(userDO);
        return UserConvertor.do2DTO(userDO);
    }


    /**
     * 更新用户token
     * @throws BaseException
     */
    public UserDTO updateToken(String code, String token) throws BaseException {

        UserDO userDO = new UserDO();
        userDO.setCode(code);
        Example<UserDO> example = Example.of(userDO);
        Optional<UserDO> findResult = userDAO.findOne(example);
        if(!findResult.isPresent()) {
            throw new BaseException("根据编码找不到用户:[" + code + "]");
        }
        userDO = findResult.get();
        userDO.setGmtModified(new Date());
        if (StringUtils.isNotEmpty(token)) {
            userDO.setToken(token);
        }
        userDO = userDAO.save(userDO);
        return UserConvertor.do2DTO(userDO);
    }

    public void deleteByCode(String code) {
        userDAO.deleteByCode(code);
    }
}
