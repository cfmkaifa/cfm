package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/select_permission_by_role_id", method = RequestMethod.GET)
    @ApiOperation("通过角色id查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleId(@Valid Long roleId){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysRolePermissionService.getPermissionByRoleId(roleId);
        result.setResult(permissionList);
        return result;
    }
    @RequestMapping(value = "/select_permission_by_role_name", method = RequestMethod.GET)
    @ApiOperation("通过角色名字查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleName(@Param("roleName")@Valid String roleName){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysRolePermissionService.getPermissionByRoleName(roleName);
        result.setResult(permissionList);
        return result;
    }

    @RequestMapping(value = "/select_permission_by_role", method = RequestMethod.GET)
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

    @RequestMapping(value = "/select_permission_in_role", method = RequestMethod.GET)
    @ApiOperation("查询角色所已拥有的权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.NOT_IN_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.IN_PERMISSION_MSG)
    })
    public Result<List<PermissionInRoleVo>> getPermissionInRole(@Param("roleId") @Valid Long roleId){
        Result<List<PermissionInRoleVo>> result = new Result<>();
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionInRole(roleId);
        result.setResult(sysPermList);
        return result;
    }

    @RequestMapping(value = "/select_permission_not_in_role", method = RequestMethod.GET)
    @ApiOperation("查询角色未拥有的权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ALL_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ALL_PERMISSION_MSG)
    })
    public Result<List<PermissionInRoleVo>> getPermissionNotInRole(@Param("roleId") @Valid Long roleId){
        Result<List<PermissionInRoleVo>> result = new Result<>();
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionNotInRole(roleId);
        result.setResult(sysPermList);
        return result;
    }

    @RequestMapping(value = "/add_permission_to_role", method = RequestMethod.POST)
    @ApiOperation("给一个角色添加一个权限")
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

    @RequestMapping(value = "/update_permission_to_role", method = RequestMethod.POST)
    @ApiOperation("修改角色的一个权限")
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

    @RequestMapping(value = "/delete_permission_to_role", method = RequestMethod.POST)
    @ApiOperation("删除角色的一个权限")
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

    @RequestMapping(value = "/update_Role_Permission_ById", method = RequestMethod.POST)
    @ApiOperation("修改角色的一个权限(根据id)")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> updateRolePermissionById(@Param("id") @Valid Long id,@Param("roleId") @Valid Long roleId ,@Param("permissionId") @Valid Long permissionId){
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
