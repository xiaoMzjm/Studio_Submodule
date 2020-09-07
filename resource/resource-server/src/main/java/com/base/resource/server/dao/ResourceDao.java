package com.base.resource.server.dao;

import java.util.List;

import com.base.resource.server.model.ResourceDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2020/4/5 8:45 PM
 */
@Repository
public interface ResourceDao extends JpaRepository<ResourceDO,Long> {

    List<ResourceDO> findByNameIn(@Param("nameList") List<String> nameList);
}
