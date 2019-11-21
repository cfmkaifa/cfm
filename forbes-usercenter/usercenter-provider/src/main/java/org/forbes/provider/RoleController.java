package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.dto.DeleteRoleDto;
import org.forbes.comm.model.AddRoleModel;
import org.forbes.comm.model.RoleModel;
import org.forbes.comm.model.UpdateRoleModel;
import org.forbes.comm.vo.LoginVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    @RequestMapping(value = "/getRoleList",method = RequestMethod.POST)
    @ApiOperation("查询用户对应角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.ROLE_ERROR_MSG),
            @ApiResponse(code=200, message = Result.ROLE_MSG)
    })
    public Result<RoleVo> selectRoleByUserId(@RequestBody @Valid RoleModel roleModel){
        Result<RoleVo> result=new Result<>();
        Long userId=roleModel.getUserId();
        List<SysRole> sysRoleList=sysRoleService.selectRoleByUserId(userId);
        if(sysRoleList==null){
            result.error500(Result.ROLE_ERROR_MSG);
            return  result;
        }else{
            RoleVo roleVo=new RoleVo();
            roleVo.setSysRoleList(sysRoleList);
            result.success(Result.ROLE_MSG);
            result.setResult(roleVo);
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
    @RequestMapping(value = "/roleList",method = RequestMethod.POST)
    @ApiOperation("查询所有角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message =Result.ROLE_LIST_MSG),
            @ApiResponse(code = 500,response =RoleListVo.class,message = Result.ROLE_LIST_ERROR_MSG)
    })
    public Result<RoleListVo> selectAllRole(){
        Result<RoleListVo> result=new Result<>();
        List<SysRole> sysRoles=sysRoleService.selectRoleList();
        RoleListVo roleListVo=new RoleListVo();
        if(sysRoles!=null&&sysRoles.size()!=0){
             roleListVo.setSysRoleList(sysRoles);
             result.setResult(roleListVo);
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
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    @ApiOperation("添加角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.ADD_ROLE_MSG),
            @ApiResponse(code=500,message = Result.ADD_ROLE_ERROR_MSG)
    })
    public Map<String,Boolean> addRole(@RequestBody @Valid AddRoleModel addRoleModel){
        Map<String,Boolean> map=new HashMap<>();
        SysRole sysRole=new SysRole();
        Integer result=sysRoleService.addRole(sysRole);
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
      *@ 时间：2019/11/21
      *@ Description：角色修改
      */
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    @ApiOperation("修改角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.UPDATE_ROLE_MSG),
            @ApiResponse(code=500,message = Result.UPDATE_ROLE_ERROR_MSG)
    })
    public Map<String,Boolean> updateRole(@RequestBody @Valid UpdateRoleModel updateRoleModel){
        Map<String,Boolean> map=new HashMap<>();
       SysRole sysRole=new SysRole();
       sysRole.setDescription(updateRoleModel.getDescription());
       sysRole.setRoleName(updateRoleModel.getRoleName());
       sysRole.setRoleCode(updateRoleModel.getRoleCode());
       Integer result=sysRoleService.updateRoleByRoleId(sysRole);
       if(result==1){
           map.put("result",true);
       }else {
           map.put("result",false);
       }
        return map;
    }

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/21
      *@ Description：删除角色
      */
    @RequestMapping(value = "/deleteRole",method = RequestMethod.POST)
    @ApiOperation("删除角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.DELETE_ROLE_MSG),
            @ApiResponse(code=500,message = Result.DELETE_ROLE_ERROR_MSG)
    })
    public Map<String,Boolean> deleteRoleByRoleId(@RequestBody @Valid DeleteRoleDto deleteRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        deleteRoleDto.setId(deleteRoleDto.getId());
        Integer result= sysRoleService.deleteRoleByRoleId(deleteRoleDto.getId());
        if(result==1){
            map.put("result",true);
        }else {
            map.put("result",false);
        }
        return map;
    }
}
