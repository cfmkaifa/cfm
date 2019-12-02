package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.ISysUserService;
import org.forbes.biz.SysUserRoleService;
import org.forbes.comm.dto.*;
import org.forbes.comm.vo.*;
import org.forbes.config.RedisUtil;
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

@RestController
@RequestMapping("/user")
@Api(tags={"用户管理"})
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private RedisUtil redisUtil;


    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/22
      *@ Description：多条件查询用户+分页
      */
    @RequestMapping(value = "/select_userlist",method = RequestMethod.POST)
    @ApiOperation("多条件查询用户")
    @ApiResponses(value = {
            @ApiResponse(code=200,response=UserListVo.class,message = Result.SELECT_LIST_USER_AND_ROLE_MSG),
            @ApiResponse(code=500, message = Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<List<UserListVo>> selectUserList(@RequestBody @Valid SysUserListDto sysUserListDto){
        Result<List<UserListVo>> result=new Result<>();
        String username=sysUserListDto.getUsername();
        String status=sysUserListDto.getStatus();
        Long roleId=sysUserListDto.getRoleId();
        String realname=sysUserListDto.getRealname();
        List<UserListVo> sysUsers=sysUserService.selectUserList(status,roleId,username,realname);

        if(sysUsers!=null){
            result.setResult(sysUsers);
            result.success(Result.SELECT_LIST_USER_AND_ROLE_MSG);
        }else {
            result.error500(Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/update_userstatus",method = RequestMethod.POST)
    @ApiOperation("根据用户名修改用户状态")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG)
    })
    public Result<CommVo> updateStausByUsername(@RequestBody @Valid UpdateStatusDto updateStatusDto){
        Result<CommVo> result=new Result<CommVo>();
        Map<String,Boolean> map=new HashMap<>();
        String username =updateStatusDto.getUsername();
        String status=updateStatusDto.getStatus();
        Integer res=sysUserService.updateUserStatus(username,status);
        CommVo comm=new CommVo();
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
      *@ 参数：user
      *@ 返回值：
      *@ 时间：2019/11/19
      * 参数不完整，需要简称，创建人，加密盐值等
      */
    @RequestMapping(value = "/add_users",method = RequestMethod.POST)
    @ApiOperation("添加用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG)
    })
    public Result<CommVo> addUser(@RequestBody @Valid AddUserDto addUserDto){
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        Map<String,Boolean> map=new HashMap<>();
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(addUserDto,sysUser);
        Integer res=sysUserService.addUser(sysUser);
        Long user_id=sysUserService.selectUserDetailByUsername(sysUser.getUsername()).getId();
        //向用户角色中间表中添加一条记录
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setCreateTime(new Date());
       // sysUserRole.setCreateBy();
        sysUserRole.setUserId(user_id);
        sysUserRole.setRoleId(addUserDto.getRoleId());
        Integer res_user_role=sysUserRoleService.addUserAndRole(sysUserRole);
        if(res==1&&res_user_role==1){
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
      *@ 时间：2019/11/19
      *@ Description：修改用户,传参不完整
      */
    @RequestMapping(value = "/update_users",method = RequestMethod.POST)
    @ApiOperation("修改用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG)
    })
    public  Result<CommVo> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        SysUser sysUser=new SysUser();
        BeanUtils.copyProperties(updateUserDto,sysUser);
        Integer res=sysUserService.updateUserByUsername(sysUser);
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
      *@ 参数：userDetailModel
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：
      */
    @RequestMapping(value = "/user-by-name",method = RequestMethod.POST)
    @ApiOperation("查询用户详情")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.DETAIL_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DETAIL_USER_MSG)
    })
    public Result<UserDeatailVo> selectUserByUsername(@RequestBody @Valid UserDetailDto userDetailDto){
        Result<UserDeatailVo> result = new Result<UserDeatailVo>();
        String username = userDetailDto.getUsername();
        UserDeatailVo sysUser = sysUserService.selectUserDetailByUsername(username);
        if(sysUser==null) {
            result.error500(Result.DETAIL_USER_EMPTY_MSG);
            return result;
        }else {
            result.setResult(sysUser);
            result.success(Result.DETAIL_USER_MSG);
        }
        return  result;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/12/2
      *@ Description：根据用户名查询角色
      */
    @RequestMapping(value = "/role-by-name",method = RequestMethod.POST)
    @ApiOperation("查询用户角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.ROLE_ERROR_MSG),
            @ApiResponse(code=200,message = Result.ROLE_MSG)
    }
    )
    public Result<List<RoleVo>>  getRoleListByUsername(@RequestBody @Valid RoleDto roleDto){
        Result<List<RoleVo>> result=new Result<>();
        String  username=roleDto.getUsername();
        List<RoleVo> sysRoleList=sysUserService.getRoleListByName(username);
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
      *@ 时间：2019/12/2
      *@ Description：根据用户名查询查询权限
      */
    @RequestMapping(value = "/permission-by-name",method = RequestMethod.POST)
    @ApiOperation("查询用户权限")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,message = Result.PERMISSIONS_MSG)
    }
    )
    public Result<List<UserPermissonVo>>  getPermissionByUsername(@RequestBody @Valid RoleDto roleDto){
        Result<List<UserPermissonVo>> result=new Result<>();
        String username=roleDto.getUsername();
        List<UserPermissonVo> sysPerList=sysUserService.getPermissonListByUsername(username);
        if(sysPerList==null){
            result.error500(Result.PERMISSIONS_NOT_ERROR_MSG);
            return  result;
        }else{
            result.success(Result.PERMISSIONS_MSG);
            result.setResult(sysPerList);
        }
        return result;
    }
}
