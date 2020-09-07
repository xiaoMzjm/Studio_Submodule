package com.base.user.server.dao;

import com.base.user.server.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2019/1/13 10:58 PM
 */
@Repository
public interface UserDAO extends JpaRepository<UserDO,String> {

    void deleteByCode(String code);
}