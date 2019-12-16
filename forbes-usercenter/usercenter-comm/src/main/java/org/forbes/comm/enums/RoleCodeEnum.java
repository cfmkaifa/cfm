package org.forbes.comm.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;
/***
 * RoleCodeEnum概要说明：角色编码
 * @author Huanghy
 */
public enum RoleCodeEnum {

	
	ADMIN("admin","超级管理员"),
	PROVIDER("provider","供应商"),
	PURCHASER("purchaser","采购商");

    /***编码
     */
    private String code;

    /***名称
     */
    private String name;


    /***
     * receUserStaus方法慨述:
     * @return List<Map<String,String>>
     * @创建人 huanghy
     * @创建时间 2019年12月7日 上午11:22:07
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public static List<Map<String,String>> receEnums(){
        return Arrays.asList(RoleCodeEnum.values()).stream().map(tEnum -> {
            Map<String,String> reponseMap = Maps.newHashMap();
            reponseMap.put("code", tEnum.getCode());
            reponseMap.put("name", tEnum.getName());
            return reponseMap;
        }).collect(Collectors.toList());
    }

    /***
     *
     * 构造函数:
     * @param code
     * @param name
     */
    RoleCodeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }




    /**
     * @return code
     */
    public String getCode() {
        return code;
    }


    /**
     * @param code 要设置的 code
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }
}
