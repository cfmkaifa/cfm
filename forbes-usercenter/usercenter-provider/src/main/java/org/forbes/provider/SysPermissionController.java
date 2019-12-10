package org.forbes.provider;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.forbes.biz.SysPermissionService;
import org.forbes.biz.SysRolePermissionService;
import org.forbes.comm.constant.CommonConstant;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.enums.BizResultEnum;
import org.forbes.comm.enums.PermissionGradeEnum;
import org.forbes.comm.enums.PermissionTypeEnum;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.model.*;
import org.forbes.comm.utils.ConvertUtils;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.comm.vo.Result;
import org.forbes.config.RedisUtil;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.tree.TreeModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/permission")
@Api(tags={"权限管理"})
@Slf4j
public class SysPermissionController {

    @Autowired
    SysPermissionService sysPermissionService;

    @Autowired
    SysRolePermissionService sysRolePermissionService;



    /***
     * selectPage方法概述: 分页多条件查询
     * @param pageDto 分页条件
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/7
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/select-page", method = RequestMethod.GET)
    @ApiOperation("分页查询权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysPermissionPageDto.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSIONS_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=SysPermission.class, message = Result.PERMISSIONS_MSG)
    })
    public Result<IPage<SysPermission>> selectPage(BasePageDto<SysPermissionPageDto> pageDto ){
        log.debug("传入参数为："+JSON.toJSONString(pageDto));
        Result<IPage<SysPermission>> result = new Result<>();
        QueryWrapper qw = new QueryWrapper<SysPermission>();
        if (pageDto.getData()!=null) {
            if(ConvertUtils.isNotEmpty(pageDto.getData().getType()) ){
                qw.eq(PermsCommonConstant.PERMS,pageDto.getData().getType());
            }
            if(ConvertUtils.isNotEmpty(pageDto.getData().getName()) ){
                qw.like(PermsCommonConstant.NAME,pageDto.getData().getName());
            }
        }
        IPage page = new Page(pageDto.getPageNo(),pageDto.getPageSize());
        IPage<SysPermission> s = sysPermissionService.page(page,qw);
        result.setResult(s);
        log.debug("返回值为:"+JSON.toJSONString(result.getResult()));
        return result;
    }


    /***
     * checkPerms方法概述: 校验权限编码唯一性
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @ApiOperation("校验权限编码是否唯一")
    @ApiImplicitParam(name = "perms",value = "权限编码",required = true)
    @RequestMapping(value = "/check-perms", method = RequestMethod.GET)
    public Result<Boolean> checkPerms(@RequestParam(value = "perms",required=true) String perms) {
        log.debug("传入参数为："+perms);
        Result<Boolean> result = new Result<>();
        result.setResult(true);//如果此参数为false则程序发生异常
            int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.PERMS,perms));
            if (exitsCount > 0){//如果编码存在，则返回false
                result.setMessage(Result.EXISTS_PERMISSION_MSG);
                result.setResult(false);
                return result;
            }else {//如果编码不存在则进行下一步
                result.setMessage(Result.AVAILABLE_PERMISSION_MSG);
                result.setResult(true);
                return result;
            }
    }



    /***
     * getPermissionById方法概述: 通过权限id查询权限内容
     * @param id 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/7
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */

    @RequestMapping(value = "/select-permission-by-id/{id}", method = RequestMethod.GET)
    @ApiOperation("通过权限id查询权限内容")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "权限id")
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.PERMISSION_BY_ID_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=PermissionVo.class, message = Result.PERMISSION_BY_ID_MSG)
    })
    public Result<SysPermission> getPermissionById(@PathVariable Long id){
        log.debug("传入id为:"+id);
        Result<SysPermission> result = new Result<>();
        SysPermission permission = sysPermissionService.getById(id);
        result.setResult(permission);
        log.debug("返回值为:"+result.getResult());
        return result;
    }


    /***
     * recePermissionTypes方法概述:获取权限类型
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/permission-types", method = RequestMethod.PUT)
    @ApiOperation("获取权限类型")
    public Result<List<Map<String,String>>> recePermissionTypes(){
        Result<List<Map<String,String>>> result = new Result<List<Map<String,String>>>();
        result.setResult(PermissionTypeEnum.recePermissionTypes());
        return result;
    }


    /***
     * receYesNos方法概述:获取是否枚举值
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/yes-nos", method = RequestMethod.PUT)
    @ApiOperation("获取是否枚举值")
    public Result<List<Map<String,String>>> receYesNos(){
        Result<List<Map<String,String>>> result = new Result<List<Map<String,String>>>();
        result.setResult(YesNoEnum.receYesNos());
        return result;
    }

    /***
     * receYesNos方法概述:获取权限等级
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/grade", method = RequestMethod.PUT)
    @ApiOperation("获取权限等级")
    public Result<List<Map<String,String>>> getGrade(){
        Result<List<Map<String,String>>> result = new Result<List<Map<String,String>>>();
        result.setResult(PermissionGradeEnum.recePermissionGrades());
        return result;
    }


    /***
     * addPermission方法概述: 仅添加一个权限
     * @param sysPermission 添加的实体
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/7
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/add-permission", method = RequestMethod.PUT)
    @ApiOperation("仅添加一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysPermission.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.ADD_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Boolean.class, message = Result.ADD_PERMISSION_MSG)
    })
    public Result<SysPermission> addPermission(SysPermission sysPermission){
        log.debug("传入sysPermission为:"+JSON.toJSONString(sysPermission));
        Result<SysPermission> result = new Result<>();
        int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(ConvertUtils.camelToUnderline(PermsCommonConstant.PERMS),sysPermission.getPerms()));
        if(exitsCount > 0){
            result.setMessage(Result.ADD_SAME_PERMISSION_NOT_ERROR_MSG);
            return result;
        }else{
            String type = String.valueOf(sysPermission.getType());
            if(!PermissionTypeEnum.existsPermissionTypeEnum(type)){
                result.setBizCode(BizResultEnum.PERMISSION_TYPE_NO_EXISTS.getBizCode());
                result.success(String.format(BizResultEnum.PERMISSION_TYPE_NO_EXISTS.getBizFormateMessage(),type));
                return result;
            }
            sysPermission.setIsHidden(YesNoEnum.NO.getCode());//是否隐藏,默认否
            sysPermission.setIsLeaf(YesNoEnum.YES.getCode());
            //判父级权权限值并更改父级自己点值
            sysPermissionService.addChangeLeaf(sysPermission);

        }
        log.debug("返回值为:"+result.getResult());
        return result;
    }


    /***
     * updatePermission方法概述: 修改权限内容
     * @param sysPermission 修改实体类
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/7
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/update-permission", method = RequestMethod.PUT)
    @ApiOperation("修改权限内容")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass=SysPermission.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.UPDATE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Boolean.class, message = Result.UPDATE_PERMISSION_MSG)
    })
    public Result<SysPermission> updatePermission(SysPermission sysPermission){
        log.debug("传入sysPermission为:"+ JSON.toJSONString(sysPermission));
        Result<SysPermission> result = new Result<>();
        SysPermission permission = sysPermissionService.getById(sysPermission.getId());
        if(permission ==null){
            result.setMessage(Result.UPDATE_PERMISSION_NOT_ERROR_MSG);
            return result;
        }
        //判断当前权限编码与输入的是否一致
        if (!permission.getPerms().equalsIgnoreCase(sysPermission.getPerms())) {
            //查询是否和其他权限编码一致
            int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.PERMS,sysPermission.getPerms()));
            if (exitsCount > 0){//已存在相同的权限编码，不操作
                result.setMessage(Result.UPDATE_SAME_PERMISSION_MSG);
            }
        }
        sysPermissionService.updateChangeLeaf(sysPermission);

        log.debug("返回值为:"+result.getResult());
        return result;
    }


    /***
     * deletePermission方法概述: 删除一个权限
     * @param id 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/7
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/delete-permission/{id}", method = RequestMethod.DELETE)
    @ApiOperation("删除一个权限")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "权限id",dataTypeClass = Long.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Boolean.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Boolean> deletePermission(@PathVariable Long id){
        log.debug("传入di为:"+id);
        Result<Boolean> result = new Result<>();
        //查询数据库有无此记录
        int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.ID,id));
        if (exitsCount == 1) {//存在该条记录
            SysPermission sysPermission = sysPermissionService.getById(id);
            //判断其是否是父级权限并更改其父级状态
           sysPermissionService.deleteChangeLeaf(sysPermission);
        }
        log.debug("返回值为:"+result.getResult());
        return result;
    }


    /***
     * deletePermissions方法概述: 批量删除权限
     * @param ids 权限id集合
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/7
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @RequestMapping(value = "/delete-permissions", method = RequestMethod.DELETE)
    @ApiOperation("批量删除权限")
    @ApiImplicitParams(
            @ApiImplicitParam(dataTypeClass =Long.class)
    )
    @ApiResponses(value={
            @ApiResponse(code=500,message= Result.DELETE_PERMISSION_NOT_ERROR_MSG),
            @ApiResponse(code=200,response=Boolean.class, message = Result.DELETE_PERMISSION_MSG)
    })
    public Result<Boolean> deletePermissions(@RequestParam(value = "ids",required = false)String  ids){
        log.debug("传入dis为:"+ids);
        Result<Boolean> result = new Result<>();
        //拿到字符串分割
        List<String> idts = Arrays.asList(ids.split(","));
        //转换类型为Long
        List<Long> idsList = idts.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
        for (Long id: idsList) {
            //查询是否有其他人是否拥有这个权限，如有，则不执行删除
            int exitsCount = sysRolePermissionService.count(new QueryWrapper<SysRolePermission>().eq(PermsCommonConstant.PERMISSION_ID,id));
            if(exitsCount > 0){//此权限使用人数大于0，不可删除
                result.setResult(false);
                result.setCode(CommonConstant.SC_INTERNAL_SERVER_ERROR_500);
                result.setMessage(Result.DELETE_IF_PERMISSION_NOT_ERROR_MSG);
              continue;
            }else if(exitsCount == 0){//此权限无人使用，可以删除
                SysPermission sysPermission = sysPermissionService.getById(id);
                //去业务层删除,并判断其是否是父级权限并更改其父级状态
                sysPermissionService.deleteChangeLeaf(sysPermission);
            }
        }
        log.debug("返回值为:"+result.getResult());
        return result;
    }
}
