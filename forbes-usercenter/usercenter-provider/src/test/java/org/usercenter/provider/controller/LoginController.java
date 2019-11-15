package org.usercenter.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName
 * @Description TODO
 * @Author xfx
 * @Date 2019/11/15 9:23
 * @Version 1.0
 **/
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("login")
    public Map<String,Object> UserLogin(){
        return null;
    }
}
