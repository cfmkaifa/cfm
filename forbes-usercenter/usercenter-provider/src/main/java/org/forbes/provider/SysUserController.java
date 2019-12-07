package org.forbes.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.forbes.biz.ISysUserService;
import org.forbes.biz.SysRoleService;
import org.forbes.biz.SysUserRoleService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.UserStausEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUserDto;
import org.forbes.comm.model.UpdateStatusDto;
import org.forbes.comm.model.UpdateUserDto;
import org.forbes.comm.vo.CommVo;
import org.forbes.comm.vo.EditorUserVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.RoleListVo;
import org.forbes.comm.vo.RoleVo;
import org.forbes.comm.vo.UserDeatailVo;
import org.forbes.comm.vo.UserPermissonVo;
import org.forbes.comm.vo.UserVo;
import org.forbes.dal.entity.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    private SysRoleService sysRoleService;

    /** 
    * @Description: 多条件查询用户+分页
    * @Param: [sysUserListDto] 
    * @return: org.forbes.comm.vo.Result<java.util.List<org.forbes.comm.vo.UserListVo>> 
    * @Author: xfx 
    * @Date: 2019/12/7 
    */
    @RequestMapping(value = "/select-userlist",method = RequestMethod.GET)
    @ApiOperation("用户分页查询")
    @ApiResponses(value = {
            @ApiResponse(code=200,response=BasePageDto.class,message = Result.SELECT_LIST_USER_AND_ROLE_MSG),
            @ApiResponse(code=500, message = Result.SELECT_LIST_USER_AND_ROLE_ERROR_MSG)
    })
    public Result<IPage<UserVo>> selectUserList(@RequestBody(required=false)BasePageDto<SysUserDto> basePageDto){
    	log.info("=============="+JSON.toJSONString(basePageDto));
    	Result<IPage<UserVo>> result=new Result<IPage<UserVo>>();
    	IPage<UserVo> page = new Page<UserVo>(basePageDto.getPageNo(),basePageDto.getPageSize());
    	IPage<UserVo> pageUsers =  sysUserService.pageUsers(page, basePageDto.getData());
        result.setResult(pageUsers);
        return result;
    }


    /**
    * @Description: 根据id修改用户状态
    * @Param: [id, status]
    * @return: org.forbes.comm.vo.Result<org.forbes.comm.vo.UserDeatailVo>
    * @Author: xfx
    * @Date: 2019/12/7
    */
    @RequestMapping(value = "/update-userstatus/{id}",method = RequestMethod.PUT)
    @ApiOperation("根据用户id修改用户状态")
    @ApiImplicitParams(value={
            @ApiImplicitParam(dataTypeClass=UpdateStatusDto.class)
    })
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG)
    })
    public Result<UserDeatailVo> updateStausById(@PathVariable Long id,@RequestParam(value="status",required=true)String status){
    	Result<UserDeatailVo> result=new Result<UserDeatailVo>();
        SysUser sysUser=sysUserService.getById(id);
        sysUser.setStatus(status);
        boolean res=sysUserService.updateById(sysUser);
        UserDeatailVo userDeatailVo=new UserDeatailVo();
       if(res){
           BeanUtils.copyProperties(sysUserService.getById(id),userDeatailVo);
           result.setResult(userDeatailVo);
        }else{
           result.error500(Result.COMM_ACTION_ERROR_MSG);
           return result;
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
    @RequestMapping(value = "/add-users",method = RequestMethod.POST)
    @ApiOperation("添加用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,response = CommVo.class,message = Result.COMM_ACTION_MSG)
    })
    public Result<SysUserDto> addUser(@RequestBody @Valid SysUserDto userDto){
    	Result<SysUserDto> result = new Result<SysUserDto>();
    	String username = userDto.getUsername();
    	int usernameCount = sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.USERNAME, username));
    	if(usernameCount > 0 ){
    		result.setBizCode(BizResultEnum.USER_NAME_EXISTS.getBizCode());
    		result.setMessage(String.format(BizResultEnum.USER_NAME_EXISTS.getBizFormateMessage(), username));
    		return result;
    	}
    	sysUserService.addUser(userDto);
    	result.setResult(userDto);
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
        //Integer res=sysUserService.updateById(entity).updateUserByUsername(sysUser);
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
        /*int existsCount = sysUserService.count(new QueryWrapper<SysUser>().eq("user_name", username));
       if(existsCount > 0 ){
    	   result.setCode(code);
    	   result.setMessage(message);
    	   return result;
       }*/
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
    @RequestMapping(value = "/editor_user",method = RequestMethod.DELETE)
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
