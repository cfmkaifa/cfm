package org.forbes.biz;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.forbes.dal.entity.SysRole;

import java.math.BigInteger;
import java.util.List;

public interface SysRoleService  extends IService<SysRole> {
    
    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：
      */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
      *@ 作者：xfx
      *@ 参数：
      *@ 返回值：
      *@ 时间：2019/11/20
      *@ Description：
      */
    Integer addRole(SysRole sysRole);

}