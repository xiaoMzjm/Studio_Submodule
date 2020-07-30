package com.base.authority.server.api;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.base.authority.client.service.RoleAuthorityService;
import com.base.authority.server.manager.RoleAuthorityManager;
import com.base.common.constant.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:小M
 * @date:2020/7/31 1:13 AM
 */
@Api(description = "权限接口")
@Controller
@RequestMapping(value = "authority", produces = {"application/json;charset=UTF-8"})
@CrossOrigin(origins = "http://192.168.1.4:8080")
@ResponseBody
public class AuthorityController {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @RequestMapping("bindauthority")
    public String bindauthority(@RequestBody BindauthorityRequest bindauthorityRequest) throws Exception{
        roleAuthorityService.add(bindauthorityRequest.roleCodeList,bindauthorityRequest.authorityCodeList);
        return JSON.toJSONString(Result.success(""));
    }
    static class BindauthorityRequest {
        public List<String> roleCodeList;
        public List<String> authorityCodeList;
    }

    @RequestMapping("listall")
    public String listAll() throws Exception{
        return JSON.toJSONString(Result.success(""));
    }

}
