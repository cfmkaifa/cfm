package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.dto.*;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/select_page", method = RequestMethod.GET)
    @ApiOperation("分页查询权限")
    @ApiImplicitParam(name = "pageDto",value = "多条件分页查询传入参数")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<IPage<SysPermission>> selectPage(@RequestBody PageDto pageDto){
        Result<IPage<SysPermission>> result = new Result<>();
        QueryWrapper qw = new QueryWrapper();
        if(pageDto.getType()!=null){
            qw.eq("type",pageDto.getType());
        }
        if(pageDto.getName()!=null && pageDto.getName()!=""){
            qw.like("name",pageDto.getName());
        }
        IPage page = new Page(pageDto.getCurrent(),pageDto.getSize());
        IPage<SysPermission> s = sysPermissionService.page(page,qw);
        result.setResult(s);
        return result;
    }

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
    @ApiImplicitParam(name = "id",value = "权限id")
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
    @ApiImplicitParam(name = "sysPermission",value = "权限实体")
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
    @ApiImplicitParam(name = "updatePermissionDto",value = "修改权限传入参数")
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

    @RequestMapping(value = "/update_permission", method = RequestMethod.POST)
    @ApiOperation("删除一个权限")
    @ApiImplicitParam(name = "id",value = "权限id")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Integer> DeletePermission(@Valid Long id){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.deletePermission(id);
        if (i!=0){
            result.success("删除权限成功！");
        }else {
            result.error500("删除权限失败！");
        }
        return result;
    }

    @RequestMapping(value = "/delete_permissions", method = RequestMethod.POST)
    @ApiOperation("删除一些权限")
    @ApiImplicitParam(name = "ids",value = "权限id集合")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Integer> DeletePermissions(@Valid List<Long> ids){
        Result<Integer> result = new Result<>();
        for (Long id : ids) {
            Integer i = sysPermissionService.deletePermission(id);
            if (i!=0){
                result.success("删除权限成功！");
            }else {
                result.error500("删除权限失败！");
            }
        }

        return result;
    }
}
