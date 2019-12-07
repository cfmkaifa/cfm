package org.forbes.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.forbes.biz.SysUserRoleService;
import org.forbes.comm.model.BatchDelUserRoleDto;
import org.forbes.comm.model.DeleteUserAndRoleDto;
import org.forbes.comm.model.RoleDto;
import org.forbes.comm.model.SelectUserAndRoleDto;
import org.forbes.comm.model.UserRoleDto;
import org.forbes.comm.vo.CommVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserRoleVo;
import org.forbes.dal.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName
 * @Description 用户角色中间表
 * @Author
 * @Date 2019/11/21 17:56
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user_role")
@Api(tags={"用户角色中间表"})
@Slf4j
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;


    /**
      *@ 作者：xfx
      *@ 参数：addUserRoleDto
      *@ 返回值：CommVo，写操作公共返回结果
      *@ 时间：2019/12/5
      *@ Description：
      */
    @RequestMapping(value = "/add_user_role",method = RequestMethod.POST)
    @ApiOperation("添加")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public Result<CommVo> addUserAndRole(@RequestBody @Valid UserRoleDto addUserRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleId(addUserRoleDto.getRoleId());
        sysUserRole.setUserId(addUserRoleDto.getUserId());
        Integer res=sysUserRoleService.addUserAndRole(sysUserRole);
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
      *@ 参数：deleteUserAndRoleDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/12/5
      *@ Description：
      */
    @RequestMapping(value = "/delete_user_role",method = RequestMethod.POST)
    @ApiOperation("删除")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<CommVo> deleteUserAndRole(@RequestBody @Valid DeleteUserAndRoleDto deleteUserAndRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        Integer res=sysUserRoleService.deleteUserAndRole(deleteUserAndRoleDto.getUserId(),deleteUserAndRoleDto.getRoleId());
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
  *@ 参数：selectUserAndRoleDto
  *@ 返回值：UserRoleVo
  *@ 时间：2019/12/5
  *@ Description：
  */
    @RequestMapping(value ="/select_user_role_userid",method = RequestMethod.GET)
    @ApiOperation("根据用户id查询用户角色中间表")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.DELETE_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.DELETE_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<List<UserRoleVo>> selectUserAndRoleByUserId(@RequestBody @Valid SelectUserAndRoleDto selectUserAndRoleDto){
        Result<List<UserRoleVo>> result=new Result<>();
        Long userId=selectUserAndRoleDto.getUserId();
        List<UserRoleVo> sysUserRoles=sysUserRoleService.selectUserRoleListByUserId(userId);
        if(sysUserRoles!=null&&sysUserRoles.size()!=0){
            result.setResult(sysUserRoles);
            result.success(Result.SELECT_USER_AND_ROLE_MSG);
        }else {
            result.error500(Result.SELECT_USER_AND_ROLE_ERROR_MSG);
            return result;
        }
        return result;
    }

    /**
      *@ 作者：xfx
      *@ 参数：roleDto
      *@ 返回值：RoleVo
      *@ 时间：2019/12/5
      *@ Description：
      */
    @RequestMapping(value ="/select_User_NotRole",method = RequestMethod.GET)
    @ApiOperation("查询用户所没有的角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.SELECT_USER_NotRole_MSG),
            @ApiResponse(code=500,message = Result.SELECT_USER_NotRole_ERROR_MSG)
    })
    public Result<List<RoleVo>> selectUserNotRole(@RequestBody @Valid RoleDto roleDto){
        Result<List<RoleVo>> result=new Result<>();
        Long userId=roleDto.getUserId();
        List<RoleVo> sysNotRoleList=sysUserRoleService.selectUserNotRole(userId);
        if(sysNotRoleList==null){
            result.error500(Result.NOT_ROLE_ERROR_MSG);
            return  result;
        }else{
            result.success(Result.NOT_ROLE_MSG);
            result.setResult(sysNotRoleList);
        }
        return result;
    }
    
    /**
      *@ 作者：xfx
      *@ 参数：batchDelUserRoleDto
      *@ 返回值：CommVo
      *@ 时间：2019/12/5
      *@ Description：用户角色中间表批量删除
      */
    @RequestMapping(value = "/batch_del_user_role",method = RequestMethod.POST)
    @ApiOperation("用户角色中间表批量删除")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG)
    })
    public Result<CommVo> deleteUserAndRole(@RequestBody @Valid BatchDelUserRoleDto batchDelUserRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        Integer res=sysUserRoleService.batchDelUserRole(batchDelUserRoleDto.getUserId(),batchDelUserRoleDto.getRoleIdArray());
        if(res!=0){
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
      *@ 参数：batchDelUserRoleDto
      *@ 返回值：CommVo
      *@ 时间：2019/12/5
      *@ Description：用户角色中间表批量添加
      */
    @RequestMapping(value = "/batch_add_user_role",method = RequestMethod.POST)
    @ApiOperation("用户角色中间表批量添加")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG)
    })
    public Result<CommVo> addUserAndRole(@RequestBody @Valid BatchDelUserRoleDto batchDelUserRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        List<UserRoleDto> addUserRoleDtoList=new ArrayList<UserRoleDto>();
        Long userId=batchDelUserRoleDto.getUserId();
        for(int i=0;i<batchDelUserRoleDto.getRoleIdArray().length;i++){
            UserRoleDto addUserRoleDto=new UserRoleDto();
            addUserRoleDto.setUserId(userId);
            addUserRoleDto.setRoleId(batchDelUserRoleDto.getRoleIdArray()[i]);
            addUserRoleDtoList.add(addUserRoleDto);
        }
        Integer res=sysUserRoleService.batchAddUserAndRole(addUserRoleDtoList);
        if(res!=0){
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
