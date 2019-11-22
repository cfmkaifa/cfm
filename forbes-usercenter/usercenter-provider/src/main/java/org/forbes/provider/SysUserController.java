package org.forbes.provider;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.ISysUserService;
import org.forbes.comm.dto.*;
import org.forbes.comm.vo.CommVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.UserDeatailVo;
import org.forbes.comm.vo.UserListVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    private RedisUtil redisUtil;


    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/22
      *@ Description：多条件查询用户+分页
      */
    @RequestMapping(value = "/selectUserList",method = RequestMethod.POST)
    @ApiOperation("多条件查询用户")
    @ApiResponses(value = {
            @ApiResponse(code=200,response=UserListVo.class,message = Result.SELECT_LIST_USER_AND_ROLE_MSG),
            @ApiResponse(code=500, message = Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<UserListVo> selectUserList(@RequestBody @Valid SysUserListDto sysUserListDto){
        Result<UserListVo> result=new Result<>();
        String username=sysUserListDto.getUsername();
        String status=sysUserListDto.getStatus();
        Long roleId=sysUserListDto.getRoleId();
        String realname=sysUserListDto.getRealname();
        Integer pageNum=sysUserListDto.getPageNum();
        Integer pageSize=sysUserListDto.getPageSize();
        PageInfo<SysUser> sysUsers=sysUserService.selectUserList(status,roleId,username,realname,pageNum,pageSize);
        UserListVo obj=new UserListVo();
        if(sysUsers!=null){
            obj.setSysUserInfo(sysUsers);
            result.setResult(obj);
            result.success(Result.SELECT_LIST_USER_AND_ROLE_MSG);
        }else {
            result.error500(Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/updateUserstatus",method = RequestMethod.POST)
    @ApiOperation("根据用户名修改用户状态")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.UPDATE_STATUS_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.UPDATE_STATUS_MSG)
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
            result.success(Result.UPDATE_STATUS_MSG);
        }else{
            result.error500(Result.UPDATE_STATUS_ERROR_MSG);
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
    @RequestMapping(value = "/addUsers",method = RequestMethod.POST)
    @ApiOperation("添加用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.ADD_USER_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.ADD_USER_MSG)
    })
    public Result<CommVo> addUser(@RequestBody @Valid AddUserDto addUserDto){
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        Map<String,Boolean> map=new HashMap<>();
        SysUser sysUser=new SysUser();
        sysUser.setAvatar(addUserDto.getAvatar());
        sysUser.setPassword(addUserDto.getPassword());
        sysUser.setPhone(addUserDto.getPhone());
        sysUser.setUsername(addUserDto.getUsername());
        Integer res=sysUserService.addUser(sysUser);
        if(res==1){
            map.put("result",true);
            comm.setMapInfo(map);
            result.setResult(comm);
            result.success(Result.ADD_USER_MSG);
        }else{
            result.error500(Result.ADD_USER_ERROR_MSG);
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
    @RequestMapping(value = "/updateUsers",method = RequestMethod.POST)
    @ApiOperation("修改用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.UPDATE_USER_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.UPDATE_USER_MSG)
    })
    public  Result<CommVo> updateUser(@RequestBody @Valid UpdateUserDto updateUserDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        SysUser sysUser=new SysUser();
        sysUser.setAvatar(updateUserDto.getAvatar());
        sysUser.setPassword(updateUserDto.getPassword());
        sysUser.setPhone(updateUserDto.getPhone());
        sysUser.setUsername(updateUserDto.getUsername());
        Integer res=sysUserService.updateUserByUsername(sysUser);
        if(res==1){
            map.put("result",true);
            comm.setMapInfo(map);
            result.setResult(comm);
            result.success(Result.UPDATE_STATUS_MSG);
        }else{
            result.error500(Result.UPDATE_STATUS_ERROR_MSG);
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
    @RequestMapping(value = "/getUserDetails",method = RequestMethod.POST)
    @ApiOperation("查询用户详情")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.DETAIL_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DETAIL_USER_MSG)
    })
    public Result<UserDeatailVo> selectUserByUsername(@RequestBody @Valid UserDetailDto userDetailDto){
        Result<UserDeatailVo> result = new Result<UserDeatailVo>();
        String username = userDetailDto.getUsername();
        SysUser sysUser = sysUserService.selectUserDetailByUsername(username);
        if(sysUser==null) {
            result.error500(Result.DETAIL_USER_EMPTY_MSG);
            return result;
        }else {
            UserDeatailVo uservo=new UserDeatailVo();
            uservo.setUserInfo(sysUser);
            result.setResult(uservo);
            result.success(Result.DETAIL_USER_MSG);
        }
        return  result;
    }
}
