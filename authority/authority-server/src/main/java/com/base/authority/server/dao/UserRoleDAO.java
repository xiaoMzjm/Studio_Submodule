package com.base.authority.server.dao;

import java.util.List;

import com.base.authority.server.model.RoleAuthorityDO;
import com.base.authority.server.model.UserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2020/8/1 1:32 AM
 */
@Repository
public interface UserRoleDAO extends JpaRepository<UserRoleDO,Long> {

    Integer deleteByUserCode(String userCode);

    List<UserRoleDO> findByUserCodeIn(List<String> userCodeList);

    @Query(nativeQuery = true, value="select * from user_role where user_code = :userCode and role_code in(:roleCodes)")
    List<UserRoleDO> findByUserCodeAndRoleCodes(String userCode, List<String> roleCodes);
}
