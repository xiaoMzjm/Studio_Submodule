package com.base.authority.server.api;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.base.authority.client.model.RoleDTO;
import com.base.authority.client.service.RoleService;
import com.base.common.annotation.ResultFilter;
import com.base.common.constant.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:小M
 * @date:2020/7/30 12:58 AM
 */
@Api(description = "角色接口")
@Controller
@RequestMapping(value = "role", produces = {"application/json;charset=UTF-8"})
@CrossOrigin(origins = "http://192.168.1.4:8080")
@ResponseBody
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("add")
    @ResultFilter
    public String add(@RequestParam String name) throws Exception{
        roleService.add(name);
        return JSON.toJSONString(Result.success(""));
    }

    @RequestMapping("listall")
    @ResultFilter
    public String listAll() throws Exception{
        List<RoleDTO> roleDTOList = roleService.selectAll();
        return JSON.toJSONString(Result.success(roleDTOList));
    }

    @RequestMapping("delete")
    @ResultFilter
    public String delete(@RequestParam String code) throws Exception{
        roleService.delete(code);
        return JSON.toJSONString(Result.success(""));
    }

    @RequestMapping("updatename")
    @ResultFilter
    public String updateName(@RequestParam String code, @RequestParam String name) throws Exception{
        roleService.updateName(code,name);
        return JSON.toJSONString(Result.success(""));
    }
}
