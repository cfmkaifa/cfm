package org.forbes.dal.mapper.ext;

import org.apache.ibatis.annotations.Param;
import org.forbes.dal.entity.SysRole;

import java.math.BigInteger;
import java.util.List;

public interface SysRoleExtMapper {
    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/20
     *@ Description：
     */
    List<SysRole> selectRoleByUserId(@Param("userId")Long userId);

    /**
     *@ 作者：xfx
     *@ 参数：
     *@ 返回值：
     *@ 时间：2019/11/20
     *@ Description：
     */
    Integer addRole(SysRole sysRole);
}
