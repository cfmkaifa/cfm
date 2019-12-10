package org.forbes.provider;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.forbes.biz.ISysUserService;
import org.forbes.biz.SysRoleService;
import org.forbes.comm.constant.DataColumnConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.model.BasePageDto;
import org.forbes.comm.model.SysUserDto;
import org.forbes.comm.model.UpdateStatusDto;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.*;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/user")
@Api(tags={"用户管理"})
@Slf4j
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

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
        log.debug("=============="+JSON.toJSONString(basePageDto));
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
        log.debug("===========id:"+JSON.toJSONString(id)+"=========status:"+JSON.toJSONString(status));
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
     * @Description:  添加用户
     * @Param: [userDto]
     * @return: org.forbes.comm.vo.Result<org.forbes.comm.model.SysUserDto>
     * @Author: xfx
     * @Date: 2019/12/9
     */
    @RequestMapping(value = "/add-users",method = RequestMethod.POST)
    @ApiOperation("添加用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG)
    })
    public Result<SysUser> addUser(@RequestBody @Valid SysUserDto userDto){
        log.debug("===========SysUserDto:"+JSON.toJSONString(userDto));
        Result<SysUser> result = new Result<SysUser>();
        String username = userDto.getUsername();
        String email=userDto.getEmail();
        String phone=userDto.getPhone();
        if(email!=null){
            int emailcount=sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.EMAIL, email));
            if(emailcount>0){
                result.setBizCode(BizResultEnum.USER_EMAIL_EXISTS.getBizCode());
                result.setMessage(String.format(BizResultEnum.USER_EMAIL_EXISTS.getBizFormateMessage(), email));
                return result;
            }
        }
        if(phone!=null){
            int phonecount=sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.PHONE, email));
            if(phonecount>0){
                result.setBizCode(BizResultEnum.PHONE_EXISTS.getBizCode());
                result.setMessage(String.format(BizResultEnum.PHONE_EXISTS.getBizFormateMessage(), phone));
                return result;
            }
        }
        int usernameCount = sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.USERNAME, username));
        if(usernameCount > 0 ){
            result.setBizCode(BizResultEnum.USER_NAME_EXISTS.getBizCode());
            result.setMessage(String.format(BizResultEnum.USER_NAME_EXISTS.getBizFormateMessage(), username));
            return result;
        }
        sysUserService.addUser(userDto);
        SysUser sysUser=sysUserService.getOne(new QueryWrapper<SysUser>().eq(DataColumnConstant.USERNAME, username));
        result.setResult(sysUser);
        return result;
    }


    /**
     * @Description:根据用户id修改用户
     * @Param: [userDto]
     * @return: org.forbes.comm.vo.Result<org.forbes.comm.model.SysUserDto>
     * @Author: xfx
     * @Date: 2019/12/7
     */
    @RequestMapping(value = "/update-users",method = RequestMethod.PUT)
    @ApiOperation("根据用户id，用户名修改用户")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.COMM_ACTION_ERROR_MSG),
            @ApiResponse(code=200,message = Result.COMM_ACTION_MSG)
    })
    public  Result<SysUser> updateUser(@RequestBody @Valid SysUserDto userDto){
        log.debug("==================用户请求参数："+JSON.toJSONString(userDto));
        Result<SysUser> result = new Result<SysUser>();
        String username=userDto.getUsername();
        int usernameCount = sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.USERNAME, username));
        String email=userDto.getEmail();
        String phone=userDto.getPhone();
        if(email!=null){
            int emailcount=sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.EMAIL, email));
            if(emailcount>0){
                result.setBizCode(BizResultEnum.USER_EMAIL_EXISTS.getBizCode());
                result.setMessage(String.format(BizResultEnum.USER_EMAIL_EXISTS.getBizFormateMessage(), email));
                return result;
            }
        }
        if(phone!=null){
            int phonecount=sysUserService.count(new QueryWrapper<SysUser>().eq(DataColumnConstant.PHONE, email));
            if(phonecount>0){
                result.setBizCode(BizResultEnum.PHONE_EXISTS.getBizCode());
                result.setMessage(String.format(BizResultEnum.PHONE_EXISTS.getBizFormateMessage(), phone));
                return result;
            }
        }
        if(usernameCount==1){
            SysUser sysUser=new SysUser();
            BeanUtils.copyProperties(userDto,sysUser);
            sysUserService.updateById(sysUser);
            result.setResult(sysUser);
        }else {
            result.setBizCode(BizResultEnum.USER_NAME_NOT_EXISTS.getBizCode());
            result.setMessage(String.format(BizResultEnum.USER_NAME_NOT_EXISTS.getBizFormateMessage(), username));
            return result;
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
    @RequestMapping(value = "/user-by-id/{id}",method = RequestMethod.GET)
    @ApiOperation("根据用户id查询用户详情")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.DETAIL_USER_ERROR_MSG),
            @ApiResponse(code=200,message = Result.DETAIL_USER_MSG)
    })
    public Result<SysUser> selectUserById(@PathVariable Long id){
        log.debug("===========id:"+JSON.toJSONString(id));
        Result<SysUser> result = new Result<SysUser>();
        SysUser sysUser = sysUserService.getById(id);
        if(sysUser==null) {
            result.setBizCode(BizResultEnum.USER_NAME_NOT_EXISTS.getBizCode());
            result.setMessage(String.format(BizResultEnum.USER_NAME_NOT_EXISTS.getBizFormateMessage(), id));
            return result;
        }else {
            result.setResult(sysUser);
        }
        return  result;
    }

    /**
     * @Description:查询用户所对应的角色
     * @Param: [username]
     * @return: org.forbes.comm.vo.Result<java.util.List<org.forbes.comm.vo.RoleVo>>
     * @Author: xfx
     * @Date: 2019/12/7
     */
    @RequestMapping(value = "/role-by-name",method = RequestMethod.GET)
    @ApiOperation("查询用户对应角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.ROLE_ERROR_MSG),
            @ApiResponse(code=200,message = Result.ROLE_MSG)
    })
    public Result<List<RoleVo>>  getRoleListByUsername(@RequestParam(value ="username",required=false)String username){
        log.debug("===========username:"+JSON.toJSONString(username));
        Result<List<RoleVo>> result=new Result<>();
        List<RoleVo> sysRoleList=sysUserService.getRoleListByName(username);
        if(sysRoleList==null||sysRoleList.size()==0){
            result.setBizCode(BizResultEnum.ROLE_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.ROLE_EXIST.getBizFormateMessage(), username));
            return result;
        }else {
            result.setResult(sysRoleList);
        }
        return result;
    }

    /**
     * @Description:  查询用户对应的权限
     * @Param: [username]
     * @return: org.forbes.comm.vo.Result<java.util.List<org.forbes.comm.vo.UserPermissonVo>>
     * @Author: xfx
     * @Date: 2019/12/7
     */
    @RequestMapping(value = "/permission-by-name",method = RequestMethod.GET)
    @ApiOperation("查询用户权限")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,message = Result.PERMISSIONS_MSG)
    }
    )
    public Result<List<UserPermissonVo>>  getPermissionByUsername(@RequestParam(name="username",required=true)String username){
        log.debug("===========username:"+JSON.toJSONString(username));
        Result<List<UserPermissonVo>> result=new Result<>();
        List<UserPermissonVo> sysPerList=sysUserService.getPermissonListByUsername(username);
        if(sysPerList==null){
            result.setBizCode(BizResultEnum.PERMISSION_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.PERMISSION_EXIST.getBizFormateMessage(), username));
            return result;
        }else{
            result.success(Result.PERMISSIONS_MSG);
            result.setResult(sysPerList);
        }
        return result;
    }

    /**
     * @Description: 编辑用户下查询用户详情，用户对应角色，所有角色
     * @Param: [id]
     * @return: org.forbes.comm.vo.Result<org.forbes.comm.vo.EditorUserVo>
     * @Author: xfx
     * @Date: 2019/12/7
     */
    @RequestMapping(value = "/select-user/{id}",method = RequestMethod.GET)
    @ApiOperation("编辑用户查询角色")
    @ApiResponses(value = {
            @ApiResponse(code=500,message = Result.EDITOR_USER_ERROR),
            @ApiResponse(code=200,message = Result.EDITOR_USER)
    })
    public Result<EditorUserVo> editorUser(@PathVariable Long id){
        log.debug("===========ID:"+JSON.toJSONString(id));
        Result<EditorUserVo> result=new Result<EditorUserVo>();
        EditorUserVo editorUserVo=new EditorUserVo();
        //查询用户
        SysUser sysUser=sysUserService.getById(id);
        UserDeatailVo userDeatailVo=new UserDeatailVo();
        BeanUtils.copyProperties(sysUser,userDeatailVo);
        editorUserVo.setUserDeatailVo(userDeatailVo);
        //查询所有角色
        List<RoleListVo> allRoleList=sysRoleService.selectRoleList();
        //查询用户对应角色
        List<RoleVo> sysRoleList=sysRoleService.selectRoleByUserId(userDeatailVo.getId());
        if(userDeatailVo!=null&&sysRoleList!=null){
            editorUserVo.setAllRoleList(allRoleList);
            editorUserVo.setRoleList(sysRoleList);
            editorUserVo.setUserDeatailVo(userDeatailVo);
            result.setResult(editorUserVo);
            result.success(Result.EDITOR_USER);
        }else {
            result.setBizCode(BizResultEnum.ROLE_EXIST.getBizCode());
            result.setMessage(String.format(BizResultEnum.ROLE_EXIST.getBizFormateMessage(), id));
            return result;
        }
        return result;
    }


    /**
     * @Description: 编辑用户，包括修改用户和修改角色
     * @Param: [sysUserDto, roles]
     * @return: org.forbes.comm.vo.Result<org.forbes.comm.vo.SysUserVo>
     * @Author: xfx
     * @Date: 2019/12/9
     */
    @ApiOperation("编辑用户")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sysUserDto",value = "用户相关信息",required = true,dataTypeClass = SysUserDto.class),
            @ApiImplicitParam(name = "roles",value = "角色IDS",required = true)
    })
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Result<SysUserVo> saveUserRole(@RequestBody @Valid SysUserDto sysUserDto){
        log.debug("================sysUserDto:"+JSON.toJSONString(sysUserDto));
        Result<SysUserVo> result=new Result<>();
        SysUser sysUser=sysUserService.getById(sysUserDto.getId());
        SysUser user=new SysUser();
        BeanUtils.copyProperties(sysUserDto,user);
        try {
            if(sysUser==null){
                result.setBizCode(BizResultEnum.USER_NAME_NOT_EXISTS.getBizCode());
                result.setMessage(String.format(BizResultEnum.USER_NAME_NOT_EXISTS.getBizFormateMessage(), sysUserDto.getId()));
                return result;
            }else {
                sysUserService.editUserWithRole(sysUserDto);
                result.success(Result.UP_SUCCESS_MSG);
            }
        } catch (Exception e){
            e.printStackTrace();
            log.debug(e.getMessage());
            result.error500(String.format(BizResultEnum.USER_NAME_EXISTS.getBizFormateMessage(), sysUserDto.getUsername()));
        }
        return result;
    }

    /**
     * @Description: 用户校验
     * @Param: [sysUserDto]
     * @return: org.forbes.comm.vo.Result<java.lang.Boolean>
     * @Author: xfx
     * @Date: 2019/12/9
     */
    @RequestMapping(value = "/check-user-unique",method = RequestMethod.GET)
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "sysUserDto",value = "用户相关信息",required = true,dataTypeClass = SysUserDto.class),
            @ApiImplicitParam(name = "roles",value = "角色IDS",required = true)
    })
    public Result<Boolean> uniqueUser(@RequestBody @Valid SysUserDto sysUserDto){
        log.debug("====================sysUserDto:"+JSON.toJSONString(sysUserDto));
        Result<Boolean> result=new Result<>();
        Long id=sysUserDto.getId();
        try {
            SysUser oldUser = null;
            if (ConvertUtils.isNotEmpty(id)) {
                oldUser = sysUserService.getById(id);
            } else {
                sysUserDto.setId(null);
            }
            SysUser newUser = sysUserService.getOne(new QueryWrapper<SysUser>().eq(DataColumnConstant.ID,sysUserDto.getId()));
            if (newUser != null) {
                //如果根据传入信息查询到用户了，那么需要做校验。
                if (oldUser == null) {
                    //oldUser为空=>新增模式=>只要用户信息存在则返回false
                    result.setSuccess(false);
                    result.setMessage(Result.UNIQUE_USER);
                    return result;
                } else if (!id.equals(newUser.getId())) {
                    //否则=>编辑模式=>判断两者ID是否一致-
                    result.setSuccess(false);
                    result.setMessage(Result.UNIQUE_USER);
                    return result;
                }
            }
        } catch (Exception e){
            result.setSuccess(false);
            result.setResult(false);
            result.setMessage(e.getMessage());
            return result;
        }
        result.setSuccess(true);
        return result;
    }
}
