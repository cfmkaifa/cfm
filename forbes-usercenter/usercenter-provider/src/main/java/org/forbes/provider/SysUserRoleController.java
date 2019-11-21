package org.forbes.provider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.forbes.comm.dto.AddUserRoleDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
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
@Api(tags={"中间表"})
@Slf4j
public class SysUserRoleController {

    @RequestMapping("/添加")
   /* @ApiResponses(value = {
            @ApiResponse(code=200,),
            @ApiResponse(code=500,)
    })*/
    public Map<String,Boolean> addUserAndRole(@RequestBody @Valid AddUserRoleDto addUserRoleDto){
        Map<String,Boolean> map=new HashMap<>();
        return map;
    }
}
