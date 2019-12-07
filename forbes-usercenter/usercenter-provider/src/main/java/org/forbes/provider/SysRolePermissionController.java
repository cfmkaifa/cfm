package org.forbes.provider;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.comm.model.AddPermissionToRoleDto;
import org.forbes.comm.model.DeletePermissionToRoleDto;
import org.forbes.comm.model.UpdatePermissionToRoleDto;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysRolePermissionVo;
import org.forbes.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lzw
 * @date 2019/12/6 9:18
 */
@RestController
@RequestMapping("/rolePermission")
@Api(tags={"角色权限管理"})
@Slf4j
public class SysRolePermissionController {

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/select-permission-by-role-id", method = RequestMethod.GET)
    @ApiOperation("通过角色id查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleId(@RequestParam(name="roleId",required=true)Long roleId){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysRolePermissionService.getPermissionByRoleId(roleId);
        result.setResult(permissionList);
        return result;
    }
    @RequestMapping(value = "/select-permission-by-role_name", method = RequestMethod.GET)
    @ApiOperation("通过角色名字查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleName(@RequestParam(name="roleName",required=true)String roleName){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysRolePermissionService.getPermissionByRoleName(roleName);
        result.setResult(permissionList);
        return result;
    }

    @RequestMapping(value = "/select-permission-by-role", method = RequestMethod.GET)
    @ApiOperation("查询所有角色与其对应的所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.NOT_IN_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.IN_PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRole(){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> sysPermList = sysRolePermissionService.getPermissionByRole();
        result.setResult(sysPermList);
        return result;
    }

    @RequestMapping(value = "/select-permission-in-role", method = RequestMethod.GET)
    @ApiOperation("查询角色所已拥有的权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.NOT_IN_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.IN_PERMISSION_MSG)
    })
    public Result<List<PermissionInRoleVo>> getPermissionInRole(@RequestParam(name="roleId",required=true)Long roleId){
        Result<List<PermissionInRoleVo>> result = new Result<>();
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionInRole(roleId);
        result.setResult(sysPermList);
        return result;
    }

    @RequestMapping(value = "/select-permission-not-in-role", method = RequestMethod.GET)
    @ApiOperation("查询角色未拥有的权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ALL_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ALL_PERMISSION_MSG)
    })
    public Result<List<PermissionInRoleVo>> getPermissionNotInRole(@RequestParam(name="roleId",required=true)Long roleId){
        Result<List<PermissionInRoleVo>> result = new Result<>();
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionNotInRole(roleId);
        result.setResult(sysPermList);
        return result;
    }

    @RequestMapping(value = "/add-permission-to-role", method = RequestMethod.PUT)
    @ApiOperation("给一个角色添加一个权限")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=AddPermissionToRoleDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> addPermissionByRole(@RequestBody @Valid AddPermissionToRoleDto addPermissionToRoleDto){
        Result<Integer> result = new Result<>();
        Integer i = sysRolePermissionService.addPermissionToRole(addPermissionToRoleDto);
        if (i!=0){
            result.success("添加权限成功！");
        }else {
            result.error500("添加权限失败！");
        }
        return result;
    }

    @RequestMapping(value = "/update-permission-to-role", method = RequestMethod.PUT)
    @ApiOperation("修改角色的一个权限")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=UpdatePermissionToRoleDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> updatePermissionToRole(@RequestBody @Valid UpdatePermissionToRoleDto updatePermissionToRoleDto){
        Result<Integer> result = new Result<>();
        Integer i = sysRolePermissionService.updatePermissionToRole(updatePermissionToRoleDto);
        if (i!=0){
            result.success("修改角色权限成功！");
        }else {
            result.error500("修改角色权限失败！");
        }

        return result;
    }

    @RequestMapping(value = "/delete-permission-to-role", method = RequestMethod.DELETE)
    @ApiOperation("删除角色的一个权限")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=DeletePermissionToRoleDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.DELETE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> deletePermissionToRole(@RequestBody @Valid DeletePermissionToRoleDto deletePermissionToRoleDto){
        Result<Integer> result = new Result<>();
        Integer i = sysRolePermissionService.deletePermissionToRole(deletePermissionToRoleDto);
        if (i!=0){
            result.success("删除角色权限成功！");
            result.setCode(200);
        }else {
            result.error("删除角色权限失败！");
            result.setCode(500);
        }
        return result;
    }

    @RequestMapping(value = "/update-Role-Permission-ById", method = RequestMethod.PUT)
    @ApiOperation("修改角色的一个权限(根据id)")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> updateRolePermissionById(@RequestParam(name="id",required=true)Long id,@RequestParam(name="roleId",required=true)Long roleId ,@RequestParam(name="permissionId",required=true)Long permissionId){
        Result<Integer> result = new Result<>();
        Integer i = sysRolePermissionService.updateRolePermissionById(id,roleId,permissionId);
        if (i!=0){
            result.success("修改角色权限成功！");
        }else {
            result.error500("修改角色权限失败！");
        }
        return result;
    }

}
