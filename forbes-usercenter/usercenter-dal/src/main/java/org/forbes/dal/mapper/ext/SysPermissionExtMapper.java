package org.forbes.dal.mapper.ext;

import org.apache.ibatis.annotations.Param;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.dal.entity.SysPermission;

import java.util.List;

/***
 * SysPermissionExtMapper：扩展类
 * @author niehy(Frunk)
 */
public interface SysPermissionExtMapper {


    /***
     * 方法概述:TODO 通过权限id查询权限内容
     * @param id 权限id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<PermissionVo> getPermissionById(@Param("id") Long id);

    /***
     * getPermission方法概述:TODO 查询所有权限
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<PermissionVo> getPermission();

    /***
     * addPermission方法概述:TODO 添加一个新的权限(仅添加权限)
     * @param sysPermission
     * @return 
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer addPermission(SysPermission sysPermission);

    /***
     * updatePermission方法概述:TODO 修改权限内容
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/11/21
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer updatePermission(SysPermission sysPermission);

}
