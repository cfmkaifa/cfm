package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.dto.AddRoleDto;
import org.forbes.comm.dto.DeleteRoleDto;
import org.forbes.comm.dto.RoleDto;
import org.forbes.comm.dto.UpdateRoleDto;
import org.forbes.comm.vo.*;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.entity.SysUserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
