package com.base.user.server.manager;

import com.base.common.exception.BaseException;
import com.base.user.server.model.UserDTO;

/**
 * @author:小M
 * @date:2019/2/17 6:50 PM
 */
public interface UserManager {

    /**
     * 根据code查询
     * @param code
     * @return
     */
    UserDTO findByCode(String code);

    /**
     * 根据token查询
     * @param token
     * @return
     */
    UserDTO findByToken(String token);

    /**
     * 保存
     */
    UserDTO save(String code, String token);


    /**
     * 更新用户token
     * @throws BaseException
     */
    UserDTO updateToken(String code, String token) throws BaseException;

    void deleteByCode(String code);
}
