package org.forbes.provider;

import java.util.List;

import javax.validation.Valid;

import org.forbes.biz.SysPermissionService;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.DeleteRoleDto;
import org.forbes.comm.model.RolePageDto;
import org.forbes.comm.model.UpdateRoleAuthorizationDto;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.RoleAuthorizationVo;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.dal.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName
 * @Description TODO
 * @Author lzw
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

    @Autowired
    SysRolePermissionService sysRolePermissionService;

    @Autowired
    SysPermissionService sysPermissionService;

    /**
      *@ 作者：lzw
      *@ 参数：roleDto
      *@ 返回值：RoleVo
      *@ 时间：2019/11/20
      *@ Description：根据用户id查询对应的角色
      */
    @RequestMapping(value = "/get-role-list",method = RequestMethod.GET)
    @ApiOperation("查询用户对应角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.ROLE_EMPTY_MSG),
            @ApiResponse(code=200, message = Result.ROLE_MSG)
    })
    public Result<List<RoleVo>> selectRoleByUserId(@RequestParam(name="roleId",required=true)Long userId){
        log.info("传入的参数为"+userId);
        Result<List<RoleVo>> result=new Result<>();
        List<RoleVo> sysRoleList=sysRoleService.selectRoleByUserId(userId);
        result.setCode(CommonConstant.SC_OK_200);
        result.success(Result.ROLE_MSG);
        result.setResult(sysRoleList);
        log.info("返回的参数为"+RoleVo.class);
        return result;
    }

    /**
      *@ 作者：lzw
      *@ 参数：
      *@ 返回值：RoleListVo
      *@ 时间：2019/11/21
      *@ Description：查询所有角色
      */
    @RequestMapping(value = "/role-list",method = RequestMethod.GET)
    @ApiOperation("查询所有角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message =Result.ROLE_LIST_MSG),
            @ApiResponse(code = 500,response =RoleListVo.class,message = Result.ROLE_LIST_ERROR_MSG)
    })
    public Result<List<RoleListVo>> selectAllRole(){
        Result<List<RoleListVo>> result=new Result<>();
        List<RoleListVo> sysRoles=sysRoleService.selectRoleList();
        result.setCode(CommonConstant.SC_OK_200);
        result.success(Result.ROLE_LIST_MSG);
        result.setResult(sysRoles);
        return result;
    }

    /**
      *@ 作者：lzw
      *@ 参数：addRoleDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/11/20
      *@ Description：
      */
    @RequestMapping(value = "/add-role",method = RequestMethod.PUT)
    @ApiOperation("添加角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<SysRole> addRole(@RequestBody @Valid SysRole sysRole){
        log.debug("传入的参数为"+ JSON.toJSONString(sysRole));
        Result<SysRole> result=new Result<SysRole>();
        String roleCode = sysRole.getRoleCode();
        int existsCount = sysRoleService.count(new QueryWrapper<SysRole>().eq(DataColumnConstant.ROLE_CODE, roleCode));
        if(existsCount > 0 ) {//存在此记录
            result.setBizCode(BizResultEnum.ROLE_CODE_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.ROLE_CODE_EXIST.getBizFormateMessage(), roleCode));
            return result;
        }
        sysRoleService.addRole(sysRole);
        result.setResult(sysRole);
        return result;
    }

    /**
      *@ 作者：lzw
      *@ 参数：updateRoleDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/11/21
      *@ Description：角色修改
      */
    @RequestMapping(value = "/update-role",method = RequestMethod.PUT)
    @ApiOperation("修改角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<Integer> updateRole(@RequestBody @Valid SysRole sysRole){
        log.info("传入的参数为"+JSON.toJSONString(sysRole));
        Result<Integer> result=new Result<>();
        Integer res=sysRoleService.updateRoleByRoleId(sysRole);
        if(res==1){
            result.success(Result.COMM_ACTION_MSG);
        }else{
            result.error500(Result.COMM_ACTION_ERROR_MSG);
        }
        log.info("返回的参数为"+SysRole.class);
        return result;
    }

    /**
      *@ 作者：lzw
      *@ 参数：deleteRoleDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/11/21
      *@ Description：删除角色
      */
    @RequestMapping(value = "/delete-role",method = RequestMethod.DELETE)
    @ApiOperation("删除角色")
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<Integer> deleteRoleByRoleId(@RequestParam(name="id",required=true)Long id ){
        log.info("传入的参数为"+id);
        Result<Integer> result=new Result<>();
        sysRoleService.removeById(id);
        //判断数据库是否有需要删除的id记录
            Integer res= sysRoleService.deleteRoleByRoleId(id);
            if(res==1){
                result.success(Result.COMM_ACTION_MSG);
            }else{
                result.error500(Result.COMM_ACTION_ERROR_MSG);
            }
            log.info("返回的参数为"+SysRole.class);
            return result;
    }

    /***
     * selectRoleAuthorization方法概述:查询角色授权
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/5 10:22
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-role-authorization",method = RequestMethod.GET)
    @ApiOperation("查询角色授权")
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<RoleAuthorizationVo> selectRoleAuthorization(@RequestParam(name="roleId",required=true)Long roleId){
        log.info("传入的参数为"+roleId);
        Result<RoleAuthorizationVo> result=new Result<>();
        RoleAuthorizationVo roleAuthorizationVo=new RoleAuthorizationVo();
        //查询角色所有权限
        List<PermissionVo> permissionList = sysPermissionService.getPermission();
        //查询角色所拥有权限
        List<PermissionInRoleVo> sysPermList = sysRolePermissionService.getPermissionInRole(roleId);
        //查询角色所有权限
        roleAuthorizationVo.setPermissionVoInfo(permissionList);
        //查询角色所拥有权限
        roleAuthorizationVo.setPermissionInRoleVoInfo(sysPermList);
        result.setResult(roleAuthorizationVo);
        log.info("返回的参数为"+RoleAuthorizationVo.class);
        return result;
    }

    /***
     * updateRoleAuthorization方法概述:修改角色授权
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/5 14:14
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-role-authorization",method = RequestMethod.PUT)
    @ApiOperation("角色授权(多个)")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=UpdateRoleAuthorizationDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<UpdateRoleAuthorizationDto> updateRoleAuthorization(@RequestBody @Valid UpdateRoleAuthorizationDto updateRoleAuthorizationDto){
        log.info("传入的参数为"+JSON.toJSONString(updateRoleAuthorizationDto));
        Result<UpdateRoleAuthorizationDto> result=new Result<>();
        try{
        	sysRoleService.updateRoleAuthorization(updateRoleAuthorizationDto);
            result.setResult(updateRoleAuthorizationDto);
        }catch(ForbesException e){
        	result.setBizCode(e.getErrorCode());
        	result.setMessage(e.getErrorMsg());
        }
        return result;
    }

    /***
     * selectRolePage方法概述:角色分页查询
     * @param
     * @return
     * @创建人 Tom
     * @创建时间 2019/12/6 10:01
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-role-page", method = RequestMethod.GET)
    @ApiOperation("分页查询权限")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=RolePageDto.class)
    })
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ROLE_LIST_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.ROLE_LIST_MSG)
    })
    public Result<IPage<SysRole>> selectRolePage(@RequestBody @Valid RolePageDto rolePageDto){
        log.info("传入的参数为"+JSON.toJSONString(rolePageDto));
        Result<IPage<SysRole>> result = new Result<>();
        QueryWrapper qw = new QueryWrapper();
        if(rolePageDto.getRoleName()!=null){
            qw.like("roleName",rolePageDto.getRoleName());
        }
        if(rolePageDto.getRoleCode()!=null && rolePageDto.getRoleCode()!=""){
            qw.like("roleCode",rolePageDto.getRoleCode());
        }
        if(rolePageDto.getDescription()!=null && rolePageDto.getDescription()!=""){
            qw.like("description",rolePageDto.getDescription());
        }
        IPage page = new Page(rolePageDto.getCurrent(),rolePageDto.getSize());
        IPage<SysRole> s = sysRoleService.page(page,qw);
        result.setResult(s);
        log.info("返回的参数为"+SysRole.class);
        return result;
    }

    /**
     *@ 作者：lzw
     *@ 参数：deleteRoleDto
     *@ 返回值：
     *@ 时间：2019/11/21
     *@ Description：删除多个角色
     */
    @RequestMapping(value = "/delete-role-ids",method = RequestMethod.DELETE)
    @ApiOperation("删除多个角色")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=DeleteRoleDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<Integer> deleteRoleByRoleIds(@RequestBody @Valid List<DeleteRoleDto> deleteRoleDto) {
        log.info("传入的参数为"+JSON.toJSONString(deleteRoleDto));
        Result<Integer> result=new Result<>();
        for (DeleteRoleDto d:deleteRoleDto){
                Integer i = sysRoleService.deleteRoleByRoleIds(d.getId());
                if (i!=0){
                    result.success("删除角色成功！");
                }else {
                    result.error500("删除角色失败！");
                }
        }
        log.info("返回的参数为"+SysRole.class);
        return result;
    }

    /**
     * 校验角色编码唯一
     */
    @ApiOperation("校验角色编码唯一")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "roleCode" ,value = "角色编码")
    })
    @RequestMapping(value = "/check-role-code", method = RequestMethod.GET)
    public Result<Boolean> checkRoleCode(@RequestParam(value="roleCode",required=true)String roleCode) {
        Result<Boolean> result = new Result<Boolean>();
        int existsCount = sysRoleService.count(new QueryWrapper<SysRole>().eq(DataColumnConstant.ROLE_CODE, roleCode));
        if(existsCount > 0 ) {//存在此记录
            result.setBizCode(BizResultEnum.ROLE_CODE_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.ROLE_CODE_EXIST.getBizFormateMessage(), roleCode));
            result.setResult(false);
            return result;
        }
        return result;
    }

}
