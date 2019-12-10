package org.forbes.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.forbes.biz.SysPermissionService;
import org.forbes.comm.constant.PermsCommonConstant;
import org.forbes.comm.enums.YesNoEnum;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.mapper.SysPermissionMapper;
import org.forbes.dal.mapper.ext.SysPermissionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper,SysPermission> implements SysPermissionService{

    @Autowired
    SysPermissionExtMapper sysPermissionExtMapper;

    @Autowired
    SysPermissionService sysPermissionService;

    /***
     * getPermission方法概述: 查询所有权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    public List<PermissionVo> getPermission() {
        return sysPermissionExtMapper.getPermission();
    }

    /***
     * addChangeLeaf方法概述: 添加判断子集
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public void addChangeLeaf(SysPermission sysPermission) {
       if(sysPermission.getParentId()!= null) {//父级id有值
           int parentIdCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.PARENT_ID,sysPermission.getParentId()));
           if (parentIdCount > 0) {//大于0说明该父节点下有其他子集(is_leaf=false)，不操作
               sysPermissionService.save(sysPermission);
           }
           if(parentIdCount == 0){//大于0说明该父节点下没有其他子集(is_leaf=true)，将是否为子节点改为否
               SysPermission parentPermission = sysPermissionService.getById(sysPermission.getParentId());
               parentPermission.setIsLeaf(YesNoEnum.NO.getCode());
               sysPermissionService.updateById(parentPermission);
               sysPermissionService.save(sysPermission);
           }
        }else {
           sysPermissionService.save(sysPermission);
       }
    }

    /***
     * updateChangeLeaf方法概述: 修改判断子集
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public void updateChangeLeaf(SysPermission sysPermission) {

        if(sysPermission.getParentId() != null){
            //查询父级权限的内容
            SysPermission parentPermission = sysPermissionService.getById(sysPermission.getParentId());
            //执行修改传入权限
            sysPermissionService.updateById(sysPermission);
            //查询父级权限
            int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.ID,parentPermission.getParentId()));
            if(exitsCount == 0){
                parentPermission.setIsLeaf(YesNoEnum.YES.getCode());
                sysPermissionService.updateById(parentPermission);
            }
        }
    }

    /***
     * deleteChangeLeaf方法概述: 删除判断子集
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    @Transactional
    public void deleteChangeLeaf(SysPermission sysPermission) {
        if(sysPermission.getParentId()!= null){//父级id有值
            //SysPermission parentSysPermission = sysPermissionService.getOne(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.ID,sysPermission.getParentId()));
            SysPermission parentSysPermission = sysPermissionService.getById(sysPermission.getParentId());
            if(parentSysPermission != null){//父级权限不为空，则把父级权限改为不是子集
                parentSysPermission.setIsLeaf(YesNoEnum.NO.getCode());
                sysPermissionService.removeById(sysPermission.getId());
                sysPermissionService.updateById(parentSysPermission);
            }
        }else {//父级id没有值，判断他的id是否为其他权限的父级id
            int exitsCount = sysPermissionService.count(new QueryWrapper<SysPermission>().eq(PermsCommonConstant.PARENT_ID,sysPermission.getId()));
            if(exitsCount > 0){//如果存在数大于0，说明该权限是其他权限的父级,则不更改子集
            }else if(exitsCount == 0){
                sysPermissionService.removeById(sysPermission.getId());
            }
        }
    }

}
