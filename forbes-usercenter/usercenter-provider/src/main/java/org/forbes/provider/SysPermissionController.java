package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.dto.RolePermissionDto;
import org.forbes.comm.dto.UpdatePermissionDto;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysRolePermissionVo;
import org.forbes.comm.vo.updatePermissionToRoleVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
@Api(tags={"权限管理"})
@Slf4j
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/getPermissionByRoleId", method = RequestMethod.POST)
    @ApiOperation("查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysPermission>> getPermissionByRoleId(Long roleId){
        Result<List<SysPermission>> result = new Result<>();
        List<SysPermission> permissionList = sysPermissionService.getPermissionByRoleId(roleId);

        result.setResult(permissionList);
        System.out.println(permissionList.toString());

        return result;
    }

    @RequestMapping(value = "/getPermissionByRole", method = RequestMethod.POST)
    @ApiOperation("查询所有角色与其对应的所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ALL_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ALL_PERMISSION_MSG)
    })
    public Result<SysRolePermissionVo> getPermissionByRole(){
        Result<SysRolePermissionVo> result = new Result<>();
        List<SysPermission> permissionByRoleList = sysPermissionService.getPermissionByRole();

        SysRolePermissionVo sysRolePermissionVo = new SysRolePermissionVo();
        sysRolePermissionVo.setSysPermissionList(permissionByRoleList);

        result.setResult(sysRolePermissionVo);
        System.out.println(sysRolePermissionVo.toString());
        return result;
    }


    @RequestMapping(value = "/addPermission", method = RequestMethod.POST)
    @ApiOperation("仅添加一个权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_PERMISSION_MSG)
    })
    public Result<Integer> addPermission(@RequestBody SysPermission sysPermission){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.addPermission(sysPermission);
        if (i!=0){
            result.success("添加权限成功！");
            result.setCode(200);
        }else {
            result.error("添加权限失败！");
            result.setCode(500);
        }
        return result;
    }

    @RequestMapping(value = "/addPermissionToRole", method = RequestMethod.POST)
    @ApiOperation("给一个角色添加一些权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> addPermissionByRole(@RequestBody RolePermissionDto rolePermissionDto){
        Result<Integer> result = new Result<>();
        List<Long> list = rolePermissionDto.getPermissionId();
        for (Long PermissionId : list) {
            Integer i = sysPermissionService.addPermissionToRole(rolePermissionDto.getRoleId(),PermissionId);
            if (i!=0){
                result.success("添加用户权限成功！");
                result.setCode(200);
            }else {
                result.error("添加用户权限失败！");
                result.setCode(500);
            }
        }
        return result;
    }

    @RequestMapping(value = "/updatePermissionById", method = RequestMethod.POST)
    @ApiOperation("修改权限内容")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<Integer> UpdatePermission(@RequestBody UpdatePermissionDto updatePermissionDto){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.updatePermissionById(updatePermissionDto.getSysPermission(),updatePermissionDto.getPermissionId());
            if (i!=0){
                result.success("修改权限内容成功！");
                result.setCode(200);
            }else {
                result.error("修改权限内容失败！");
                result.setCode(500);
            }
            return result;
    }

    @RequestMapping(value = "/updatePermissionToRole", method = RequestMethod.POST)
    @ApiOperation("修改角色的一个权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> updatePermissionToRole(@RequestBody updatePermissionToRoleVo updatePermissionToRoleVo){
        Result<Integer> result = new Result<>();

            Integer i = sysPermissionService.updatePermissionToRole(updatePermissionToRoleVo.getId(),updatePermissionToRoleVo.getPermissionId(),updatePermissionToRoleVo.getRoleId());
            if (i!=0){
                result.success("修改角色权限成功！");
                result.setCode(200);
            }else {
                result.error("修改角色权限失败！");
                result.setCode(500);
            }

        return result;
    }

    @RequestMapping(value = "/deletePermissionToRole", method = RequestMethod.POST)
    @ApiOperation("删除角色的一个权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.DELETE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> deletePermissionToRole(@RequestBody SysRolePermission sysRolePermission){
        Result<Integer> result = new Result<>();
            Integer i = sysPermissionService.deletePermissionToRole(sysRolePermission.getPermissionId(),sysRolePermission.getRoleId());
            if (i!=0){
                result.success("删除角色权限成功！");
                result.setCode(200);
            }else {
                result.error("删除角色权限失败！");
                result.setCode(500);
            }
        return result;
    }
}
