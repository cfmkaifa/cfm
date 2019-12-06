package org.forbes.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.SysPermissionService;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.dto.*;
import org.forbes.comm.vo.*;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/20 11:42
 * @Version 1.0
 **/
@RestController
@RequestMapping("/role")
@Api(tags={"用户角色"})
@Slf4j
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Autowired
    SysPermissionService sysPermissionService;

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：根据用户id查询对应的角色
      */
    @RequestMapping(value = "/get_role_list",method = RequestMethod.GET)
    @ApiOperation("查询用户对应角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.ROLE_ERROR_MSG),
            @ApiResponse(code=200, message = Result.ROLE_MSG)
    })
    public Result<List<RoleVo>> selectRoleByUserId(@RequestBody @Valid RoleDto roleDto){
        Result<List<RoleVo>> result=new Result<>();
        Long userId=roleDto.getUserId();
        List<RoleVo> sysRoleList=sysRoleService.selectRoleByUserId(userId);
        if(sysRoleList==null){
            result.error500(Result.ROLE_ERROR_MSG);
            return  result;
        }else{
            result.success(Result.ROLE_MSG);
            result.setResult(sysRoleList);
        }
        return result;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/21
      *@ Description：查询所有角色
      */
    @RequestMapping(value = "/role_list",method = RequestMethod.GET)
    @ApiOperation("查询所有角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message =Result.ROLE_LIST_MSG),
            @ApiResponse(code = 500,response =RoleListVo.class,message = Result.ROLE_LIST_ERROR_MSG)
    })
    public Result<List<RoleListVo>> selectAllRole(){
        Result<List<RoleListVo>> result=new Result<>();
        List<RoleListVo> sysRoles=sysRoleService.selectRoleList();
        if(sysRoles!=null&&sysRoles.size()!=0){
             result.setResult(sysRoles);
             result.success(Result.ROLE_LIST_MSG);
        }else{
            result.error500(Result.ROLE_LIST_ERROR_MSG);
        }
        return result;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：
      */
    @RequestMapping(value = "/add_role",method = RequestMethod.POST)
    @ApiOperation("添加角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<CommVo> addRole(@RequestBody @Valid AddRoleDto addRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        SysRole sysrole=new SysRole();
        BeanUtils.copyProperties(addRoleDto,sysrole);
        sysrole.setCreateTime(new Date());
        Integer res=sysRoleService.addRole(sysrole);
        if(res==1){
            map.put("result",true);
            comm.setMapInfo(map);
            result.setResult(comm);
            result.success(Result.COMM_ACTION_MSG);
        }else{
            result.error500(Result.COMM_ACTION_ERROR_MSG);
            map.put("result",false);
        }
        return result;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/21
      *@ Description：角色修改
      */
    @RequestMapping(value = "/update_role",method = RequestMethod.POST)
    @ApiOperation("修改角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<CommVo> updateRole(@RequestBody @Valid UpdateRoleDto updateRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        SysRole sysRole=new SysRole();
        BeanUtils.copyProperties(updateRoleDto,sysRole);
       Integer res=sysRoleService.updateRoleByRoleId(sysRole);
        if(res==1){
            map.put("result",true);
            comm.setMapInfo(map);
            result.setResult(comm);
            result.success(Result.COMM_ACTION_MSG);
        }else{
            result.error500(Result.COMM_ACTION_ERROR_MSG);
            map.put("result",false);
        }
        return result;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/21
      *@ Description：删除角色
      */
    @RequestMapping(value = "/delete_role",method = RequestMethod.POST)
    @ApiOperation("删除角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<CommVo> deleteRoleByRoleId(@RequestBody @Valid DeleteRoleDto deleteRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        deleteRoleDto.setId(deleteRoleDto.getId());
        Integer res= sysRoleService.deleteRoleByRoleId(deleteRoleDto.getId());
        if(res==1){
            map.put("result",true);
            comm.setMapInfo(map);
            result.setResult(comm);
            result.success(Result.COMM_ACTION_MSG);
        }else{
            result.error500(Result.COMM_ACTION_ERROR_MSG);
            map.put("result",false);
        }
        return result;
    }

    /***
     * selectRoleAuthorization方法概述:查询角色授权
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/5 10:22
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select_Role_Authorization",method = RequestMethod.GET)
    @ApiOperation("查询角色授权")
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<RoleAuthorizationVo> selectRoleAuthorization(@Param("roleId") @Valid Long roleId){
        Result<RoleAuthorizationVo> result=new Result<>();
        RoleAuthorizationVo roleAuthorizationVo=new RoleAuthorizationVo();
        //查询角色所有权限
        List<PermissionVo> permissionList = sysPermissionService.getPermission();
        //查询角色所拥有权限
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionInRole(roleId);
        //查询角色所有权限
        roleAuthorizationVo.setPermissionVoInfo(permissionList);
        //查询角色所拥有权限
        roleAuthorizationVo.setPermissionInRoleVoInfo(sysPermList);
        result.setResult(roleAuthorizationVo);
        return result;
    }

    /***
     * updateRoleAuthorization方法概述:修改角色授权
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/5 14:14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update_Role_Authorization",method = RequestMethod.POST)
    @ApiOperation("修改角色授权")
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<Integer> updateRoleAuthorization(@RequestBody @Valid List<UpdateRoleAuthorizationDto> updateRoleAuthorizationDto){
        Result<Integer> result=new Result<>();
        //遍历传入的dto
        for (UpdateRoleAuthorizationDto s:updateRoleAuthorizationDto){
            Integer i = sysRolePermissionService.updateRolePermissionById(s.getId(),s.getRoleId(),s.getPermissionId());
            if (i!=0){
                result.success("修改角色权限成功！");
            }else {
                result.error500("修改角色权限失败！");
            }

        }
        return result;
    }

    /***
     * selectRolePage方法概述:角色分页查询
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/6 10:01
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select_Role_Page", method = RequestMethod.GET)
    @ApiOperation("分页查询权限")
    @ApiImplicitParam(name = "rolePageDto",value = "角色多条件分页查询传入参数")
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public Result<IPage<SysRole>> selectRolePage(@RequestBody RolePageDto rolePageDto){
        Result<IPage<SysRole>> result = new Result<>();
        QueryWrapper qw = new QueryWrapper();
        if(rolePageDto.getRoleName()!=null){
            qw.like("roleName",rolePageDto.getRoleName());
        }
        if(rolePageDto.getRoleCode()!=null && rolePageDto.getRoleCode()!=""){
            qw.like("roleCode",rolePageDto.getRoleCode());
        }
        if(rolePageDto.getDescription()!=null && rolePageDto.getDescription()!=""){
            qw.like("description",rolePageDto.getDescription());
        }
        IPage page = new Page(rolePageDto.getCurrent(),rolePageDto.getSize());
        IPage<SysRole> s = sysRoleService.page(page,qw);
        result.setResult(s);
        return result;
    }

}
