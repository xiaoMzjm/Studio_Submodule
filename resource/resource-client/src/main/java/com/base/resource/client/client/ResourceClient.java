package com.base.resource.client.client;

import java.util.*;

import com.base.resource.client.model.ResourceDTO;
import com.base.resource.client.model.ResourceVO;

/**
 * @author:小M
 * @date:2020/4/5 9:23 PM
 */
public interface ResourceClient {

    Map<String,ResourceVO> findByNameList(List<String> nameList);

    ResourceDTO add(String path, String name, String ext, String oriName);
}
