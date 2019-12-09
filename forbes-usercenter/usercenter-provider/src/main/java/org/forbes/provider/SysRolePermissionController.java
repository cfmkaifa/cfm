package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.UpdatePermissionToRoleDto;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysRolePermissionVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysRolePermission;
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
        log.debug("传入的参数为"+roleId);
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysRolePermissionService.getPermissionByRoleId(roleId);
        result.setResult(permissionList);
        log.info("返回的参数为"+SysRolePermissionVo.class);
        return result;
    }
    @RequestMapping(value = "/select-permission-by-role-name", method = RequestMethod.GET)
    @ApiOperation("通过角色名字查询角色所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_MSG)
    })
    public Result<List<SysRolePermissionVo>> getPermissionByRoleName(@RequestParam(name="roleName",required=true)String roleName){
        log.debug("传入的参数为"+roleName);
        Result<List<SysRolePermissionVo>> result = new Result<>();
        List<SysRolePermissionVo> permissionList = sysRolePermissionService.getPermissionByRoleName(roleName);
        result.setResult(permissionList);
        log.info("返回的参数为"+SysRolePermissionVo.class);
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
        log.info("返回的参数为"+SysRolePermissionVo.class);
        return result;
    }

    @RequestMapping(value = "/select-permission-in-role", method = RequestMethod.GET)
    @ApiOperation("查询角色所已拥有的权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.NOT_IN_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.IN_PERMISSION_MSG)
    })
    public Result<List<PermissionInRoleVo>> getPermissionInRole(@RequestParam(name="roleId",required=true)Long roleId){
        log.debug("传入的参数为"+roleId);
        Result<List<PermissionInRoleVo>> result = new Result<>();
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionInRole(roleId);
        result.setResult(sysPermList);
        log.info("返回的参数为"+PermissionInRoleVo.class);
        return result;
    }

    @RequestMapping(value = "/select-permission-not-in-role", method = RequestMethod.GET)
    @ApiOperation("查询角色未拥有的权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ALL_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ALL_PERMISSION_MSG)
    })
    public Result<List<PermissionInRoleVo>> getPermissionNotInRole(@RequestParam(name="roleId",required=true)Long roleId){
        log.debug("传入的参数为"+roleId);
        Result<List<PermissionInRoleVo>> result = new Result<>();
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionNotInRole(roleId);
        result.setResult(sysPermList);
        log.info("返回的参数为"+PermissionInRoleVo.class);
        return result;
    }

    @RequestMapping(value = "/add-permission-to-role", method = RequestMethod.PUT)
    @ApiOperation("给一个角色添加一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysRolePermission.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ADD_ROLE_PERMISSION_MSG)
    })
    public Result<SysRolePermission> addPermissionByRole(@RequestBody @Valid SysRolePermission sysRolePermission){
        log.debug("传入的参数为"+JSON.toJSONString(sysRolePermission));
        Result<SysRolePermission> result = new Result<SysRolePermission>();
        Long permissionId = sysRolePermission.getPermissionId();
        int existsCount = sysRolePermissionService.count(new QueryWrapper<SysRolePermission>().eq(DataColumnConstant.PERMISSION_ID, permissionId));
        if(existsCount > 0 ) {//存在此记录
            result.setBizCode(BizResultEnum.PERMISSION_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PERMISSION_EXIST.getBizFormateMessage(), permissionId));
            return result;
        }
        sysRolePermissionService.addPermissionToRole(sysRolePermission);
        result.setResult(sysRolePermission);
        log.info("返回的参数为"+SysRolePermission.class);
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
    public Result<UpdatePermissionToRoleDto> updatePermissionToRole(@RequestBody @Valid UpdatePermissionToRoleDto updatePermissionToRoleDto){
        log.debug("传入的参数为"+ JSON.toJSONString(updatePermissionToRoleDto));
        Result<UpdatePermissionToRoleDto> result = new Result<UpdatePermissionToRoleDto>();
        Long permissionId = updatePermissionToRoleDto.getPermissionId();
        int existsCount = sysRolePermissionService.count(new QueryWrapper<SysRolePermission>().eq(DataColumnConstant.PERMISSION_ID, permissionId));
        if(existsCount > 0 ) {//存在此记录
            result.setBizCode(BizResultEnum.PERMISSION_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PERMISSION_EXIST.getBizFormateMessage(), permissionId));
            return result;
        }
        sysRolePermissionService.updatePermissionToRole(updatePermissionToRoleDto);
        result.setResult(updatePermissionToRoleDto);
        return result;
    }

    @RequestMapping(value = "/delete-permission-to-role", method = RequestMethod.DELETE)
    @ApiOperation("删除角色的一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysRolePermission.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.DELETE_ROLE_PERMISSION_MSG)
    })
    public Result<SysRolePermission> deletePermissionToRole(@RequestBody @Valid SysRolePermission sysRolePermission){
        log.debug("传入的参数为"+JSON.toJSONString(sysRolePermission));
        Result<SysRolePermission> result = new Result<SysRolePermission>();
        Long permissionId = sysRolePermission.getPermissionId();
        int existsCount = sysRolePermissionService.count(new QueryWrapper<SysRolePermission>().eq(DataColumnConstant.PERMISSION_ID, permissionId));
        if(existsCount !=1 ) {//不存在此记录
            result.setBizCode(BizResultEnum.PERMISSION_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PERMISSION_EXIST.getBizFormateMessage(), permissionId));
            return result;
        }
        sysRolePermissionService.deletePermissionToRole(sysRolePermission);
        result.setResult(sysRolePermission);
        return result;
    }
}
