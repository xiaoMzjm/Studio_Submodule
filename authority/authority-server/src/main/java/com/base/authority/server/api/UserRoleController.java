package com.base.authority.server.api;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.base.authority.client.service.RoleService;
import com.base.authority.client.service.UserRoleService;
import com.base.common.annotation.ResultFilter;
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
 * @date:2020/8/1 1:47 AM
 */
@Api(description = "用户角色接口")
@Controller
@RequestMapping(value = "userrole", produces = {"application/json;charset=UTF-8"})
@CrossOrigin(origins = "http://192.168.1.4:8080")
@ResponseBody
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("bind")
    @ResultFilter
    public String bind(@RequestBody BindRequest bindRequest) throws Exception{
        userRoleService.bind(bindRequest.userCodeList, bindRequest.roleCodeList);
        return JSON.toJSONString(Result.success(""));
    }
    static class BindRequest {
        public List<String> roleCodeList;
        public List<String> userCodeList;
    }
}
