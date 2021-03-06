package com.base.authority.server.dao;

import java.util.List;

import com.base.authority.server.model.AuthorityDO;
import com.base.authority.server.model.RoleAuthorityDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2020/7/31 12:50 AM
 */
@Repository
public interface RoleAuthorityDAO extends JpaRepository<RoleAuthorityDO,Long> {

    List<RoleAuthorityDO> findByRoleCodeIn(List<String> roleCodeList);

    List<RoleAuthorityDO> findByPowerCodeIn(List<String> authCode);
}
