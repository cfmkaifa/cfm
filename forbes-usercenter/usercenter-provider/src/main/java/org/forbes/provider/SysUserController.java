package org.forbes.provider;

import java.util.*;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.forbes.biz.ISysUserService;
import org.forbes.biz.SysRoleService;
import org.forbes.biz.SysUserRoleService;
import org.forbes.comm.dto.*;
import org.forbes.comm.utils.PasswordUtil;
import org.forbes.comm.utils.UUIDGenerator;
import org.forbes.comm.vo.*;
import org.forbes.dal.entity.SysUser;
import org.forbes.dal.entity.SysUserRole;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("/user")
@Api(tags={"用户管理"})
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;


    /**
      *@ 作者：xfx
      *@ 参数：sysUserListDto
      *@ 返回值：UserListVo
      *@ 时间：2019/11/22
      *@ Description：多条件查询用户+分页
      */
    @RequestMapping(value = "/select_userlist",method = RequestMethod.GET)
    @ApiOperation("多条件查询用户")
    @ApiResponses(value = {
            @ApiResponse(code=200,response=UserListVo.class,message = Result.SELECT_LIST_USER_AND_ROLE_MSG),
            @ApiResponse(code=500, message = Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<List<UserListVo>> selectUserList(@RequestBody(required = false)SysUserListDto sysUserListDto){
        Result<List<UserListVo>> result=new Result<>();
        List<UserListVo> sysUsers=sysUserService.selectUserList(sysUserListDto);
        if(sysUsers!=null){
            result.setResult(sysUsers);
            result.success(Result.SELECT_LIST_USER_AND_ROLE_MSG);
        }else {
            result.error500(Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG);
            return result;
        }
        return result;
    }
    /**
      *@ 作者：xfx
      *@ 参数：updateStatusDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/12/5
      *@ Description：根据用户名修改用户状态
      */
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
      *@ 参数：addUserDto
      *@ 返回值：CommVo,写操作公共返回结果
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
        sysUser.setSalt(UUIDGenerator.generateString(8));
        //密码加密
        String userpassword = PasswordUtil.encrypt(sysUser.getUsername(), sysUser.getPassword(), sysUser.getSalt());
        sysUser.setPassword(userpassword);
        Integer res=sysUserService.addUser(sysUser);
        //向用户角色中间表中添加一条记录
        Long user_id=sysUserService.selectUserDetailByUsername(sysUser.getUsername()).getId();
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleId(addUserDto.getRoleId());
        sysUserRole.setUserId(user_id);
        Integer user_role_res=sysUserRoleService.addUserAndRole(sysUserRole);
        if(res==1&&user_role_res==1){
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
      *@ 参数：updateUserDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/11/19
      *@ Description：修改用户
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
      *@ 参数：username,用户名
      *@ 返回值：UserDeatailVo，用户详细信息
      *@ 时间：2019/11/20
      *@ Description：
      */
    @RequestMapping(value = "/user-by-name",method = RequestMethod.GET)
    @ApiOperation("查询用户详情")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.DETAIL_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DETAIL_USER_MSG)
    })
    public Result<UserDeatailVo> selectUserByUsername(@RequestParam(name="username",required=true)String username){
        Result<UserDeatailVo> result = new Result<UserDeatailVo>();
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
      *@ 参数：username
      *@ 返回值：RoleVo
      *@ 时间：2019/12/2
      *@ Description：根据用户名查询角色
      */
    @RequestMapping(value = "/role-by-name",method = RequestMethod.GET)
    @ApiOperation("查询用户角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.ROLE_ERROR_MSG),
            @ApiResponse(code=200,message = Result.ROLE_MSG)
    }
    )
    public Result<List<RoleVo>>  getRoleListByUsername(@RequestParam(name="username",required=true)String username){
        Result<List<RoleVo>> result=new Result<>();
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
      *@ 参数：username
      *@ 返回值：UserPermissonVo
      *@ 时间：2019/12/2
      *@ Description：根据用户名查询权限
      */
    @RequestMapping(value = "/permission-by-name",method = RequestMethod.GET)
    @ApiOperation("查询用户权限")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,message = Result.PERMISSIONS_MSG)
    }
    )
    public Result<List<UserPermissonVo>>  getPermissionByUsername(@RequestParam(name="username",required=true)String username){
        Result<List<UserPermissonVo>> result=new Result<>();
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

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/12/5
      *@ Description：用户分配角色
      */
    @RequestMapping(value = "/editor_user",method = RequestMethod.GET)
    @ApiOperation("编辑用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.EDITOR_USER_ERROR),
            @ApiResponse(code=200,message = Result.EDITOR_USER)
    })
    public Result<EditorUserVo> EditorUser(@Param("username")String username){
        Result<EditorUserVo> result=new Result<EditorUserVo>();
        EditorUserVo editorUserVo=new EditorUserVo();
        //查询用户详情
        UserDeatailVo userDeatailVo=sysUserService.selectUserDetailByUsername(username);
        //查询所有角色
        List<RoleListVo> allRoles=sysRoleService.selectRoleList();
        //查询用户对应角色
        List<RoleVo> sysRoleList=sysRoleService.selectRoleByUserId(userDeatailVo.getId());
        if(userDeatailVo!=null&&sysRoleList!=null){
            editorUserVo.setAllRoleList(allRoles);
            editorUserVo.setRoleList(sysRoleList);
            editorUserVo.setUserDeatailVo(userDeatailVo);
            result.setResult(editorUserVo);
            result.success(Result.EDITOR_USER);
        }else {
            result.error500(Result.EDITOR_USER_ERROR);
            return result;
        }
        return result;
    }
}
