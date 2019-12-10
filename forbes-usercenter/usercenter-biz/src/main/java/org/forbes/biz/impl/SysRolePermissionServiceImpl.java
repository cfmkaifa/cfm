package org.forbes.biz.impl;

import org.forbes.biz.ISysRolePermissionService;
import org.forbes.dal.entity.SysRolePermission;
import org.forbes.dal.mapper.SysRolePermissionMapper;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class SysRolePermissionServiceImpl
        extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {
	
}
