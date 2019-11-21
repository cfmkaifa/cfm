package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.ISysUserService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.model.AddUserModel;
import org.forbes.comm.model.UpdateStatusModel;
import org.forbes.comm.model.UpdateUserModel;
import org.forbes.comm.model.UserDetailModel;
import org.forbes.comm.utils.JwtUtil;
import org.forbes.comm.vo.LoginVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysUserListVo;
import org.forbes.comm.vo.UserDeatailVo;
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
public class UserController {

    @Autowired
    private ISysUserService sysUserService;


    @Autowired
    private RedisUtil redisUtil;


    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ApiOperation("查询用户列表")
    @ApiResponses(value={//未定义响应结果字符串
            @ApiResponse(code=500,message= Result.LOGIN_NOT_USER_ERROR_MSG),
            @ApiResponse(code=200,response=SysUserListVo.class, message = Result.LOGIN_MSG)
    })
    public Result<SysUserListVo> getSysUserList(){
        Result<SysUserListVo> result = new Result<>();
        List<SysUser> userList = sysUserService.getUserList();

        SysUserListVo listVo = new SysUserListVo();
        listVo.setUserList(userList);
        result.setResult(listVo);
        return result;
    }

    @RequestMapping(value = "/updateUserStatus",method = RequestMethod.POST)
    @ApiOperation("修改用户状态")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.UPDATE_STATUS_ERROR_MSG),
            @ApiResponse(code=200,message = Result.UPDATE_STATUS_MSG)
    })
    public Map<String,Boolean> updateStausByUsername(@RequestBody @Valid UpdateStatusModel updateStatusModel){
        Map<String,Boolean> map=new HashMap<>();
        String username =updateStatusModel.getUsername();
        String status=updateStatusModel.getStatus();
        Integer result=sysUserService.updateUserStatus(username,status);
        if(result==1){
            map.put("result",true);
        }else{
            map.put("result",false);
        }
        return map;
    }

    /**
      *@ 作者：xfx
      *@ 参数：user
      *@ 返回值：
      *@ 时间：2019/11/19
      * 参数不完整，需要简称，创建人，加密盐值等
      */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation("添加用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.ADD_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.ADD_USER_MSG)
    })
    public Map<String,Boolean> addUser(@RequestBody @Valid AddUserModel addUserModel){
        Map<String,Boolean> map=new HashMap<>();
        SysUser sysUser=new SysUser();
        sysUser.setAvatar(addUserModel.getAvatar());
        sysUser.setPassword(addUserModel.getPassword());
        sysUser.setPhone(addUserModel.getPhone());
        sysUser.setUsername(addUserModel.getUsername());
        Integer result=sysUserService.addUser(sysUser);
        if(result==1){
            map.put("result",true);
        }else{
            map.put("result",false);
        }
        return map;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/19
      *@ Description：修改用户,传参不完整
      */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation("修改用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.UPDATE_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.UPDATE_USER_MSG)
    })
    public Map<String,Boolean> updateUser(@RequestBody @Valid UpdateUserModel updateUserModel){
        Map<String,Boolean> map=new HashMap<>();
        SysUser sysUser=new SysUser();
        sysUser.setAvatar(updateUserModel.getAvatar());
        sysUser.setPassword(updateUserModel.getPassword());
        sysUser.setPhone(updateUserModel.getPhone());
        sysUser.setUsername(updateUserModel.getUsername());
        Integer result=sysUserService.updateUserByUsername(sysUser);
        if(result==1){
            map.put("result",true);
        }else{
            map.put("result",false);
        }
        return map;
    }

    /**
      *@ 作者：xfx
      *@ 参数：userDetailModel
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：
      */
    @RequestMapping(value = "/getUserDetail",method = RequestMethod.POST)
    @ApiOperation("查询用户详情")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.DETAIL_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DETAIL_USER_MSG)
    })
    public Result<UserDeatailVo> selectUserByUsername(@RequestBody @Valid UserDetailModel userDetailModel){
        Result<UserDeatailVo> result = new Result<UserDeatailVo>();
        String username = userDetailModel.getUsername();
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
