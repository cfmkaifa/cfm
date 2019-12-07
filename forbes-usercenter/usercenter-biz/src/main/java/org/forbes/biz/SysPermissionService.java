package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.forbes.comm.model.UpdatePermissionDto;
import org.forbes.comm.vo.PermissionVo;
import org.forbes.dal.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {

    /***
     * 方法概述:TODO 通过权限id查询权限内容
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/2
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    List<PermissionVo> getPermissionById(Long id);

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
    Integer updatePermission(UpdatePermissionDto updatePermissionDto);


    /***
     * deletePermission方法概述:TODO 删除一个权限
     * @param id
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/5
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer deletePermission(@Param("id") Long id);

    /***
     * deletePermissions方法概述:TODO 批量删除权限
     * @param ids
     * @return
     * @创建人 niehy(Frunk)
     * @创建时间 2019/12/5
     * @修改人 (修改了该文件，请填上修改人的名字)
     * @修改日期 (请填上修改该文件时的日期)
     */
    Integer deletePermissions(List<Long> ids);


}
