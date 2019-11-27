package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysUserRoleService;
import org.forbes.comm.dto.AddUserRoleDto;
import org.forbes.comm.dto.DeleteUserAndRoleDto;
import org.forbes.comm.dto.SelectUserAndRoleDto;
import org.forbes.comm.utils.JwtUtil;
import org.forbes.comm.vo.CommVo;
import org.forbes.comm.vo.LoginVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.UserAndRoleVo;
import org.forbes.dal.entity.SysUserRole;
import org.omg.CORBA.PUBLIC_MEMBER;
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

    @RequestMapping(value = "/add_user_role",method = RequestMethod.POST)
    @ApiOperation("添加")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.ADD_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.ADD_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<CommVo> addUserAndRole(@RequestBody @Valid AddUserRoleDto addUserRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Result<CommVo> result=new Result<CommVo>();
        CommVo comm=new CommVo();
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleId(addUserRoleDto.getRoleId());
        sysUserRole.setUserId(addUserRoleDto.getUserId());
       // sysUserRole.setCreateBy(JwtUtil.);
        //sysUserRole.setCreateTime(new Date());
        Integer res=sysUserRoleService.addUserAndRole(sysUserRole);
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

    @RequestMapping(value = "/delete_user_role",method = RequestMethod.POST)
    @ApiOperation("删除")
    @ApiResponses(value = {
            @ApiResponse(code=200,response = CommVo.class,message = Result.DELETE_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.DELETE_USER_AND_ROLE_ERROR_MSG)
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
            result.success(Result.UPDATE_STATUS_MSG);
        }else{
            result.error500(Result.UPDATE_STATUS_ERROR_MSG);
            map.put("result",false);
        }
        return result;
    }

    @RequestMapping(value ="/select_user_role_userid",method = RequestMethod.POST)
    @ApiOperation("根据用户id查询用户角色中间表")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.DELETE_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.DELETE_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<List<UserAndRoleVo>> selectUserAndRoleByUserId(@RequestBody @Valid SelectUserAndRoleDto selectUserAndRoleDto){
        Result<List<UserAndRoleVo>> result=new Result<>();
        Long userId=selectUserAndRoleDto.getUserId();
        List<UserAndRoleVo> sysUserRoles=sysUserRoleService.selectUserRoleListByUserId(userId);
        if(sysUserRoles!=null&&sysUserRoles.size()!=0){
            result.setResult(sysUserRoles);
            result.success(Result.SELECT_USER_AND_ROLE_MSG);
        }else {
            result.error500(Result.SELECT_USER_AND_ROLE_ERROR_MSG);
            return result;
        }
        return result;
    }
}
