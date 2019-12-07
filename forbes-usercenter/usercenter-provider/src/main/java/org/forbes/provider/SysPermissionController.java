package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysPermissionService;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.model.*;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/permission")
@Api(tags={"权限管理"})
@Slf4j
public class SysPermissionController {


    @Autowired
    SysPermissionService sysPermissionService;

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/select-page", method = RequestMethod.GET)
    @ApiOperation("分页查询权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=PageDto.class)
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
        result.setCode(CommonConstant.SC_OK_200);
        result.success(Result.PERMISSIONS_MSG);
        return result;
    }

    @RequestMapping(value = "/select-permission-by-id/{id}", method = RequestMethod.GET)
    @ApiOperation("通过权限id查询权限内容")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "权限id")
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_BY_ID_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=PermissionVo.class, message = Result.PERMISSION_BY_ID_MSG)
    })
    public Result<SysPermission> getPermissionById(@PathVariable Long id){
        Result<SysPermission> result = new Result<>();
        SysPermission permission = sysPermissionService.getById(id);
        result.setCode(CommonConstant.SC_OK_200);
        result.success(Result.PERMISSION_BY_ID_MSG);
        result.setResult(permission);
        return result;
    }

    @RequestMapping(value = "/add-permission", method = RequestMethod.PUT)
    @ApiOperation("仅添加一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysPermission.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.ADD_PERMISSION_MSG)
    })
    public Result<Integer> addPermission(@RequestBody @Valid SysPermission sysPermission){
        Result<Integer> result = new Result<>();
        boolean i = sysPermissionService.save(sysPermission);
        if (i!=true){
            result.setCode(CommonConstant.SC_OK_200);
            result.success(Result.ADD_PERMISSION_MSG);
        }else {
            result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
            result.error500(Result.ADD_PERMISSION_NOT_ERROR_MSG);
        }
        return result;
    }

    @RequestMapping(value = "/update-permission", method = RequestMethod.PUT)
    @ApiOperation("修改权限内容")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysPermission.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<Integer> updatePermission(@RequestBody @Valid SysPermission sysPermission){
        Result<Integer> result = new Result<>();
        boolean i = sysPermissionService.updateById(sysPermission);
        if (i!=true){
            result.setCode(CommonConstant.SC_OK_200);
            result.success(Result.UPDATE_PERMISSION_MSG);
        }else {
            result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
            result.error500(Result.UPDATE_PERMISSION_NOT_ERROR_MSG);
        }
        return result;
    }

    @RequestMapping(value = "/delete-permission/{id}", method = RequestMethod.DELETE)
    @ApiOperation("删除一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "权限id",dataTypeClass = Long.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Boolean> deletePermission(@PathVariable Long id){
        Result<Boolean> result = new Result<>();
        //查询数据库有无此记录
        int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq("id",id));
        if (exitsCount == 1){//存在该条记录
            boolean i = sysPermissionService.removeById(id);
            if (i ==true ){//可以删除
                result.setCode(CommonConstant.SC_OK_200);
                result.setMessage(Result.DELETE_PERMISSION_MSG);
            }else if(i == false ){//删除失败
                result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
                result.error500(Result.DELETE_IF_PERMISSION_NOT_ERROR_MSG);
            }
            result.setResult(i);
        }else {//权限不存在
            result.setResult(false);
            result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
            result.error500(Result.DELETE_PERMISSION_NOT_NULL_ERROR_MSG);
        }

        return result;
    }

    @RequestMapping(value = "/delete-permissions", method = RequestMethod.DELETE)
    @ApiOperation("批量删除权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass =Long.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Integer.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Boolean> deletePermissions(@RequestParam(value = "ids",required = false)String  ids){
        Result<Boolean> result = new Result<>();
        //拿到字符串分割
        List<String> idts = Arrays.asList(ids.split(","));
        //转换类型为Long
        List<Long> idsList = idts.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
        for (Long id: idsList) {
            //查询是否有其他人是否拥有这个权限，如有，则不执行删除
            int exitsCount = sysRolePermissionService.count(new QueryWrapper<SysRolePermission>().eq("permission_id",id));
            if(exitsCount > 0){//此权限使用人数大于0，不可删除
                result.setResult(false);
                result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
                result.error500(Result.DELETE_IF_PERMISSION_NOT_ERROR_MSG);
              continue;
            }else if(exitsCount == 0){//此权限无人使用，可以删除
                boolean i = sysPermissionService.removeById(id);
                if (i ==true ){//删除成功
                    result.setCode(CommonConstant.SC_OK_200);
                    result.success(Result.DELETE_PERMISSION_MSG);
                }else if(i == false ){//删除失败
                    result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
                    result.error500(Result.DELETE_PERMISSION_NOT_ERROR_MSG);
                }
                result.setResult(i);
            }
        }
        return result;
    }
}
