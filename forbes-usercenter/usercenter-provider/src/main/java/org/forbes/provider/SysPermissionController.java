package org.forbes.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.dto.AddPermissionToRoleDto;
import org.forbes.comm.dto.DeletePermissionToRoleDto;
import org.forbes.comm.dto.UpdatePermissionDto;
import org.forbes.comm.dto.UpdatePermissionToRoleDto;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysRolePermissionVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
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

    @RequestMapping(value = "/select_permission", method = RequestMethod.GET)
    @ApiOperation("查询所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<List<PermissionVo>> getPermission(){
        Result<List<PermissionVo>> result = new Result<>();
        List<PermissionVo> permissionList = sysPermissionService.getPermission();
        result.setResult(permissionList);
        return result;
    }

    @RequestMapping(value = "/select_permission_by_role_id", method = RequestMethod.GET)
    @ApiOperation("通过角色id查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleId(@Valid Long roleId){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysPermissionService.getPermissionByRoleId(roleId);
        result.setResult(permissionList);
        return result;
    }
    @RequestMapping(value = "/select_permission_by_role_name", method = RequestMethod.GET)
    @ApiOperation("通过角色名字查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleName(@Valid String roleName){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysPermissionService.getPermissionByRoleName(roleName);
        result.setResult(permissionList);
        return result;
    }

    @RequestMapping(value = "/select_permission_by_role", method = RequestMethod.GET)
    @ApiOperation("查询所有角色与其对应的所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ALL_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ALL_PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRole(){
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> sysPermList = sysPermissionService.getPermissionByRole();
        result.setResult(sysPermList);
        return result;
    }

    @RequestMapping(value = "/add_permission", method = RequestMethod.POST)
    @ApiOperation("仅添加一个权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_PERMISSION_MSG)
    })
    public Result<Integer> addPermission(@RequestBody @Valid SysPermission sysPermission){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.addPermission(sysPermission);
        if (i!=0){
            result.success("添加权限成功！");
        }else {
            result.error500("添加权限失败！");
        }
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

        Integer i = sysPermissionService.addPermissionToRole(addPermissionToRoleDto);
        if (i!=0){
            result.success("添加权限成功！");
        }else {
            result.error500("添加权限失败！");
        }
        return result;
    }

    @RequestMapping(value = "/update_permission", method = RequestMethod.POST)
    @ApiOperation("修改权限内容")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<Integer> UpdatePermission(@RequestBody @Valid UpdatePermissionDto updatePermissionDto){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.updatePermission(updatePermissionDto);
            if (i!=0){
                result.success("修改权限内容成功！");
            }else {
                result.error500("修改权限内容失败！");
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

            Integer i = sysPermissionService.updatePermissionToRole(updatePermissionToRoleDto);
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
            Integer i = sysPermissionService.deletePermissionToRole(deletePermissionToRoleDto);
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
