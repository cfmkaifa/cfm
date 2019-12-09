package org.forbes.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.forbes.biz.SysUserRoleService;
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
@RequestMapping("/user-role")
@Api(tags={"用户角色中间表"})
@Slf4j
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

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
}
