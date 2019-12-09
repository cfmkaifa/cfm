package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.dal.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

    /***
     * getPermission方法概述: 查询所有权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<PermissionVo> getPermission();


    /***
     * addChangeLeaf方法概述: 添加判断子集
     * @param sysPermission 权限对象
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Boolean addChangeLeaf(SysPermission sysPermission);

    /***
     * updateChangeLeaf方法概述: 修改判断子集
     * @param sysPermission 权限对象
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Boolean updateChangeLeaf(SysPermission sysPermission);

    /***
     * deleteChangeLeaf方法概述: 删除判断子集
     * @param sysPermission 权限对象
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/9
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Boolean deleteChangeLeaf(SysPermission sysPermission);

}
