package com.base.authority.server.api;

import java.util.HashMap;
import java.util.*;

import com.alibaba.fastjson.JSON;

import com.base.authority.client.model.AuthorityVO;
import com.base.authority.client.client.AuthorityClient;
import com.base.authority.client.client.RoleAuthorityClient;
import com.base.common.annotation.ResultFilter;
import com.base.common.constant.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:小M
 * @date:2020/7/31 1:13 AM
 */
@Api(description = "权限接口")
@Controller
@RequestMapping(value = "authority", produces = {"application/json;charset=UTF-8"})
@CrossOrigin(origins = "http://192.168.50.196:8080")
@ResponseBody
public class AuthorityController {

    @Autowired
    private RoleAuthorityClient roleAuthorityService;
    @Autowired
    private AuthorityClient authorityService;

    @RequestMapping("bindauthority")
    @ResultFilter
    public String bindauthority(@RequestBody BindauthorityReq bindauthorityReq) throws Exception{
        Map<String,List<String>> roleAndAuthorityListMap = new HashMap<>();
        for(BindauthorityRequest request : bindauthorityReq.array) {
            roleAndAuthorityListMap.put(request.roleCode, request.authorityCodeList);
        }
        roleAuthorityService.add(roleAndAuthorityListMap);
        return JSON.toJSONString(Result.success(""));
    }
    static class BindauthorityReq {
        public List<BindauthorityRequest> array;
    }
    static class BindauthorityRequest {
        public String roleCode;
        public List<String> authorityCodeList;
    }

    @RequestMapping("listall")
    @ResultFilter
    public String listAll() throws Exception {
        List<AuthorityVO> authorityVOList = authorityService.selectAllAuthorityAndRole();
        return JSON.toJSONString(Result.success(authorityVOList));
    }

}
