package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.model.*;
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


    @RequestMapping(value = "/select-page", method = RequestMethod.GET)
    @ApiOperation("分页查询权限")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "pageDto",dataTypeClass=PageDto.class,required = true)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=SysPermission.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<IPage<SysPermission>> selectPage(@RequestBody @Valid PageDto pageDto){
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

    @RequestMapping(value = "/select-permission", method = RequestMethod.GET)
    @ApiOperation("查询所有权限")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=PermissionVo.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<List<PermissionVo>> getPermission(){
        Result<List<PermissionVo>> result = new Result<>();
        List<PermissionVo> permissionList = sysPermissionService.getPermission();
        result.setResult(permissionList);
        return result;
    }

    @RequestMapping(value = "/select-permission-by-id", method = RequestMethod.GET)
    @ApiOperation("通过权限id查询权限内容")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",required = true)
    )
    @ApiImplicitParam(name = "id",value = "权限id")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_BY_ID_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=PermissionVo.class, message = Result.PERMISSION_BY_ID_MSG)
    })
    public Result<SysPermission> getPermissionById(@RequestParam("id") @Valid Long id){
        Result<SysPermission> result = new Result<>();
        SysPermission permission = sysPermissionService.getById(id);
        result.setResult(permission);
        return result;
    }

    @RequestMapping(value = "/add-permission", method = RequestMethod.PUT)
    @ApiOperation("仅添加一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "sysPermission",value = "权限实体类",dataTypeClass = SysPermission.class,required = true)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.ADD_PERMISSION_MSG)
    })
    public Result<Integer> addPermission(@RequestBody @Valid SysPermission sysPermission){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.addPermission(sysPermission);
        if (i!=0){
            result.success(Result.ADD_PERMISSION_MSG);
        }else {
            result.error500(Result.ADD_PERMISSION_NOT_ERROR_MSG);
        }
        return result;
    }

    @RequestMapping(value = "/update-permission", method = RequestMethod.PUT)
    @ApiOperation("修改权限内容")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "sysPermission",dataTypeClass = SysPermission.class,required = true)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<Integer> updatePermission(@RequestBody @Valid UpdatePermissionDto updatePermissionDto){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.updatePermission(updatePermissionDto);
        if (i!=0){
            result.success(Result.UPDATE_PERMISSION_MSG);
        }else {
            result.error500(Result.UPDATE_PERMISSION_NOT_ERROR_MSG);
        }
        return result;
    }

    @RequestMapping(value = "/delete-permission", method = RequestMethod.DELETE)
    @ApiOperation("删除一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "权限id")
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Integer> deletePermission(@RequestParam("id") @Valid Long id){
        Result<Integer> result = new Result<>();
        Integer i = sysPermissionService.deletePermission(id);
        if (i ==1 ){
            result.success(Result.DELETE_PERMISSION_MSG);
        }else if(i == -2 ){
            result.error500(Result.DELETE_IF_PERMISSION_NOT_ERROR_MSG);
        }else if (i == 0){
            result.error500(Result.DELETE_PERMISSION_NOT_ERROR_MSG);
        }
        return result;
    }

    @RequestMapping(value = "/delete-permissions", method = RequestMethod.DELETE)
    @ApiOperation("批量删除权限")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "ids",value = "权限id集合")
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Integer> deletePermissions(@RequestBody @Valid List<Long> ids){
        Result<Integer> result = new Result<>();
            Integer i = sysPermissionService.deletePermissions(ids);
            if (i ==1 ){
                result.success(Result.DELETE_PERMISSION_MSG);
            }else if(i == -1 ){
                result.error500(Result.DELETE_IF_PERMISSION_NOT_ERROR_MSG);
            }else if (i ==0 ){
                result.error500(Result.DELETE_PERMISSION_NOT_ERROR_MSG);
            }
        return result;
    }
}
