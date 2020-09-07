package com.base.resource.server.manager.impl;

import java.util.Date;
import java.util.List;

import com.base.resource.client.model.ResourceDTO;
import com.base.resource.server.dao.ResourceDao;
import com.base.resource.server.manager.ResourceManager;
import com.base.resource.server.model.ResourceConvertor;
import com.base.resource.server.model.ResourceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/9/8 1:33 AM
 */
@Component
public class ResourceManagerImpl implements ResourceManager {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public ResourceDTO add(String path, String name, String ext, String oriName) {

        ResourceDO resourceDO = new ResourceDO();
        Date now = new Date();
        resourceDO.setGmtCreate(now);
        resourceDO.setGmtModified(now);
        resourceDO.setPath(path);
        resourceDO.setName(name);
        resourceDO.setExt(ext);
        resourceDO.setOriName(oriName);
        resourceDO = resourceDao.save(resourceDO);
        return ResourceConvertor.do2dto(resourceDO);
    }

    @Override
    public List<ResourceDTO> findByNames(List<String> nameList){
        List<ResourceDO> result = resourceDao.findByNameIn(nameList);
        return ResourceConvertor.do2dtos(result);
    }
}
