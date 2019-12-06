package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.dto.UpdatePermissionDto;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
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

    @RequestMapping(value = "/select_permission_by_id", method = RequestMethod.GET)
    @ApiOperation("通过权限id查询权限内容")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_BY_ID_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSION_BY_ID_MSG)
    })
    public Result<List<PermissionVo>> getPermissionById(@Param("id") @Valid Long id){
        Result<List<PermissionVo>> result = new Result<>();
        List<PermissionVo> permissionList = sysPermissionService.getPermissionById(id);
        result.setResult(permissionList);
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

}
