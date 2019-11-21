package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.dto.RolePermissionDto;
import org.forbes.comm.dto.SysPermissionDto;
import org.forbes.comm.dto.SysRoleDto;
import org.forbes.comm.dto.SysUserDto;
import org.forbes.comm.utils.JwtUtil;
import org.forbes.comm.vo.LoginVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.UserPermissionVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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


    @RequestMapping(value = "/getPermission", method = RequestMethod.POST)
    @ApiOperation("查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<UserPermissionVo> getPermissionByRoleId(@RequestBody @Valid Integer RoleId){
        Result<UserPermissionVo> result = new Result<>();
        List<SysPermission> permissionList = sysPermissionService.getPermissionByRoleId(RoleId);

        UserPermissionVo userPermissionVo = new UserPermissionVo();
        userPermissionVo.setSysPermissionList(permissionList);

        result.setResult(userPermissionVo);
        System.out.println(userPermissionVo.toString());
        return result;
    }

    @RequestMapping(value = "/AddPermission", method = RequestMethod.POST)
    @ApiOperation("仅添加一个权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_PERMISSION_MSG)
    })
    public Result<Integer> AddPermission(@RequestBody @Valid SysPermissionDto sysPermissionDto){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.AddPermission(sysPermissionDto.getSysPermission());
        if (i!=0){
            result.success("添加权限成功！");
            result.setCode(200);
        }else {
            result.error("添加权限失败！");
            result.setCode(500);
        }
        return result;
    }

    @RequestMapping(value = "/AddPermissionToUser", method = RequestMethod.POST)
    @ApiOperation("给一个用户添加一些权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> AddPermissionByRole(@RequestBody @Valid RolePermissionDto rolePermissionDto){
        Result<Integer> result = new Result<>();
        List<Integer> list = rolePermissionDto.getPermissionIdList();
        for (Integer PermissionId:list) {
            Integer i = sysPermissionService.AddPermissionToRole(rolePermissionDto.getRoleId(),PermissionId);
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

    @RequestMapping(value = "/UpdatePermissionById", method = RequestMethod.POST)
    @ApiOperation("通过权限id修改权限内容")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<Integer> UpdatePermission(@RequestBody @Valid Integer PermissionId){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.UpdatePermissionById(PermissionId);
            if (i!=0){
                result.success("修改权限内容成功！");
                result.setCode(200);
            }else {
                result.error("修改权限内容失败！");
                result.setCode(500);
            }
            return result;
    }

    @RequestMapping(value = "/UpdatePermissionToRole", method = RequestMethod.POST)
    @ApiOperation("修改一个角色的某些权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> UpdatePermissionToRole(@RequestBody @Valid RolePermissionDto rolePermissionDto){
        Result<Integer> result = new Result<>();
        List<Integer> list = rolePermissionDto.getPermissionIdList();

        for (Integer PermissionId:list) {
            Integer i = sysPermissionService.UpdatePermissionToRole(rolePermissionDto.getRoleId(),PermissionId);
            if (i!=0){
                result.success("修改角色权限成功！");
                result.setCode(200);
            }else {
                result.error("修改角色权限失败！");
                result.setCode(500);
            }
        }

        return result;
    }

    @RequestMapping(value = "/DeletePermissionToRole", method = RequestMethod.POST)
    @ApiOperation("删除角色的一些权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.DELETE_ROLE_PERMISSION_MSG)
    })
    public Result<Integer> DeletePermissionToRole(@RequestBody @Valid RolePermissionDto rolePermissionDto){
        Result<Integer> result = new Result<>();
        List<Integer> list = rolePermissionDto.getPermissionIdList();
        for (Integer PermissionId:list) {
            Integer i = sysPermissionService.DeletePermissionToRole(rolePermissionDto.getRoleId(),PermissionId);
            if (i!=0){
                result.success("删除角色权限成功！");
                result.setCode(200);
            }else {
                result.error("删除角色权限失败！");
                result.setCode(500);
            }
        }
        return result;
    }
}
