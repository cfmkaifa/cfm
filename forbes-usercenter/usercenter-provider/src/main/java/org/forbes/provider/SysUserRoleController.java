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
@RequestMapping("/userRole")
@Api(tags={"用户角色中间表"})
@Slf4j
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @RequestMapping(value = "/addUserAndRole",method = RequestMethod.POST)
    @ApiOperation("添加")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.ADD_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.ADD_USER_AND_ROLE_ERROR_MSG)
    })
    public Map<String,Boolean> addUserAndRole(@RequestBody @Valid AddUserRoleDto addUserRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        SysUserRole sysUserRole=new SysUserRole();
        sysUserRole.setRoleId(addUserRoleDto.getRoleId());
        sysUserRole.setUserId(addUserRoleDto.getUserId());
       // sysUserRole.setCreateBy(JwtUtil.);
        //sysUserRole.setCreateTime(new Date());
        Integer result=sysUserRoleService.addUserAndRole(sysUserRole);
        if(result==1){
            map.put("result",true);
        }else {
            map.put("result",false);
        }
        return map;
    }

    @RequestMapping(value = "/deleteUserAndRole",method = RequestMethod.POST)
    @ApiOperation("删除")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.DELETE_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.DELETE_USER_AND_ROLE_ERROR_MSG)
    })
    public Map<String,Boolean> deleteUserAndRole(@RequestBody @Valid DeleteUserAndRoleDto deleteUserAndRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        Integer result=sysUserRoleService.deleteUserAndRole(deleteUserAndRoleDto.getUserId(),deleteUserAndRoleDto.getRoleId());
        if(result==1){
            map.put("result",true);
        }else {
            map.put("result",false);
        }
        return map;
    }

    @RequestMapping(value ="/selectUserAndRoleByUserId",method = RequestMethod.POST)
    @ApiOperation("根据用户id查询用户角色中间表")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.DELETE_USER_AND_ROLE_MSG),
            @ApiResponse(code=500,message = Result.DELETE_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<UserAndRoleVo> selectUserAndRoleByUserId(@RequestBody @Valid SelectUserAndRoleDto selectUserAndRoleDto){
        Result<UserAndRoleVo> result=new Result<>();
        Long userId=selectUserAndRoleDto.getUserId();
        List<SysUserRole> sysUserRoles=sysUserRoleService.selectUserRoleListByUserId(userId);
        UserAndRoleVo userAndRoleVo=new UserAndRoleVo();
        if(sysUserRoles!=null&&sysUserRoles.size()!=0){
            userAndRoleVo.setSysUserRoleList(sysUserRoles);
            result.setResult(userAndRoleVo);
            result.success(Result.SELECT_USER_AND_ROLE_MSG);
        }else {
            result.error500(Result.SELECT_USER_AND_ROLE_ERROR_MSG);
            return result;
        }
        return result;
    }
}
