package com.base.resource.server.manager;

import java.util.Date;
import java.util.List;

import com.base.resource.server.dao.ResourceDao;
import com.base.resource.server.model.ResourceConvertor;
import com.base.resource.server.model.ResourceDO;
import com.base.resource.client.model.ResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/4/5 8:46 PM
 */
public interface ResourceManager {


    ResourceDTO add(String path, String name, String ext, String oriName);

    List<ResourceDTO> findByNames(List<String> nameList);

}
