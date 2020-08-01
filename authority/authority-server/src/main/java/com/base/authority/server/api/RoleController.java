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
import org.springframework.web.bind.annotation.RequestBody;
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
@CrossOrigin(origins = "http://192.168.50.196:8080")
@ResponseBody
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("add")
    @ResultFilter
    public String add(@RequestBody AddRequest addRequest) throws Exception{
        roleService.add(addRequest.name);
        return JSON.toJSONString(Result.success(""));
    }
    public static class AddRequest {
        public String name;
    }


    @RequestMapping("listall")
    @ResultFilter
    public String listAll() throws Exception{
        List<RoleDTO> roleDTOList = roleService.selectAll();
        return JSON.toJSONString(Result.success(roleDTOList));
    }

    @RequestMapping("delete")
    @ResultFilter
    public String delete(@RequestBody DeleteRequest deleteRequest) throws Exception{
        roleService.delete(deleteRequest.code);
        return JSON.toJSONString(Result.success(""));
    }
    public static class DeleteRequest{
        public String code;
    }

    @RequestMapping("updatename")
    @ResultFilter
    public String updateName(@RequestBody UpdateNameRequest updateNameRequest) throws Exception{
        roleService.updateName(updateNameRequest.code,updateNameRequest
        .name);
        return JSON.toJSONString(Result.success(""));
    }
    public static class UpdateNameRequest{
        public String code;
        public String name;
    }
}
