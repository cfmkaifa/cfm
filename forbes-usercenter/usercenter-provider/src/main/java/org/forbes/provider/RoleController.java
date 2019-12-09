package org.forbes.provider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.forbes.biz.SysPermissionService;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.exception.ForbesException;
import org.forbes.comm.model.*;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.PermissionInRoleVo;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.RoleAuthorizationVo;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.dal.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
 * @Description 角色控制
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
    @RequestMapping(value = "/get-role-list/{userId}",method = RequestMethod.GET)
    @ApiOperation("查询用户对应角色")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "userId",value = "用户id")
    )
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.ROLE_EMPTY_MSG),
            @ApiResponse(code=200, message = Result.ROLE_MSG)
    })
    public Result<List<RoleVo>> selectRoleByUserId(@PathVariable Long userId){
        log.debug("传入的参数为"+userId);
        Result<List<RoleVo>> result=new Result<>();
        List<RoleVo> sysRoleList=sysRoleService.selectRoleByUserId(userId);
        result.success(Result.ROLE_MSG);
        result.setResult(sysRoleList);
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
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysRole.class)
    )
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
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysRole.class)
    )
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<SysRole> updateRole(@RequestBody @Valid SysRole sysRole){
        log.debug("传入的参数为"+JSON.toJSONString(sysRole));
        Result<SysRole> result=new Result<SysRole>();
        String roleCode = sysRole.getRoleCode();
        SysRole sysRole1 = sysRoleService.getById(sysRole.getId());
        if(sysRole1 ==null){
            result.setMessage(Result.UPDATE_ROLE_ERROR_MSG);
            return result;
        }
        //判断当前角色编码与输入的是否一致
        if (!sysRole1.getRoleCode().equalsIgnoreCase(sysRole.getRoleCode())) {
            //查询是否和其他角色编码一致
            int existsCount = sysRoleService.count(new QueryWrapper<SysRole>().eq(DataColumnConstant.ROLE_CODE, roleCode));
            if (existsCount > 0) {//存在此记录
                result.setBizCode(BizResultEnum.ROLE_CODE_EXIST.getBizCode());
                result.setMessage(String.format(BizResultEnum.ROLE_CODE_EXIST.getBizFormateMessage(), roleCode));
                return result;
            }
        }
        sysRoleService.updateRoleByRoleId(sysRole);
        result.setResult(sysRole);
        return result;
    }

    /**
      *@ 作者：lzw
      *@ 参数：deleteRoleDto
      *@ 返回值：CommVo,写操作公共返回结果
      *@ 时间：2019/11/21
      *@ Description：删除角色
      */
    @RequestMapping(value = "/delete-role/{id}",method = RequestMethod.DELETE)
    @ApiOperation("删除角色")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "角色id")
    )
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<SysRole> deleteRoleByRoleId(@PathVariable Long id){
        log.debug("传入的参数为"+id);
        Result<SysRole> result=new Result<SysRole>();
        int existsCount = sysRoleService.count(new QueryWrapper<SysRole>().eq(DataColumnConstant.ID, id));
        if(existsCount != 1 ) {//不存在此记录
            result.setBizCode(BizResultEnum.ROLE_ID_NOT_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.ROLE_ID_NOT_EXIST.getBizFormateMessage(), id));
            return result;
        }
        sysRoleService.deleteRoleByRoleId(id);
        result.success("删除角色成功！");
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
    @ApiImplicitParams(
            @ApiImplicitParam(name = "roleId",value = "角色id")
    )
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<RoleAuthorizationVo> selectRoleAuthorization(@PathVariable Long roleId){
        log.debug("传入的参数为"+roleId);
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
            @ApiImplicitParam(dataTypeClass=RolePermissionDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code=500,message= Result.UPDATE_ROLE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Result.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<RolePermissionDto> updateRoleAuthorization(@RequestBody @Valid RolePermissionDto rolePermissionDto){
        log.debug("传入的参数为"+JSON.toJSONString(rolePermissionDto));
        Result<RolePermissionDto> result=new Result<>();
        try{
        	sysRoleService.updateRoleAuthorization(rolePermissionDto);
            result.setResult(rolePermissionDto);
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
    public Result<IPage<SysRole>> selectRolePage(BasePageDto<RolePageDto> pageDto){
        log.debug("传入的参数为"+JSON.toJSONString(pageDto));
        Result<IPage<SysRole>> result = new Result<>();
        QueryWrapper qw = new QueryWrapper();
        if(ConvertUtils.isNotEmpty(pageDto.getData().getRoleName())){
            qw.like(PermsCommonConstant.ROLE_NAME,pageDto.getData().getRoleName());
        }
        if(ConvertUtils.isNotEmpty(pageDto.getData().getRoleCode())){
            qw.eq(PermsCommonConstant.ROLE_CODE,pageDto.getData().getRoleCode());
        }
        IPage page = new Page(pageDto.getPageNo(),pageDto.getPageSize());
        IPage<SysRole> s = sysRoleService.page(page,qw);
        result.setResult(s);
        result.success(Result.ROLE_LIST_MSG);
        log.info("返回值为:"+JSON.toJSONString(result.getResult()));
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
            @ApiImplicitParam(dataTypeClass=RoleDtos.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG),
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG)
    })
    public  Result<SysRole> deleteRoleByRoleIds(@RequestParam(value = "ids",required = false) String ids) {
        log.debug("传入dis为:"+ids);
        Result<SysRole> result=new Result<SysRole>();
        //拿到字符串分割
        List<String> idts = Arrays.asList(ids.split(","));
        //转换类型为Long
        List<Long> idsList = idts.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
        for (Long d:idsList){
            int existsCount = sysRoleService.count(new QueryWrapper<SysRole>().eq(DataColumnConstant.ID, d));
            if(existsCount != 1 ) {//不存在此记录
                result.setBizCode(BizResultEnum.ROLE_ID_NOT_EXIST.getBizCode());
                result.setMessage(String.format(BizResultEnum.ROLE_ID_NOT_EXIST.getBizFormateMessage(), d));
                return result;
            }
            sysRoleService.deleteRoleByRoleIds(d);
            result.success("删除角色成功！");
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
