package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.ISysUserService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.utils.JwtUtil;
import org.forbes.comm.vo.LoginVo;
import org.forbes.comm.vo.Result;
import org.forbes.comm.vo.SysUserListVo;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        System.out.println(listVo.toString());
        return result;
    }

}
