package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysPermission;

import java.io.Serializable;
import java.util.List;

@ApiModel(description="权限返回对象")
@Data
public class UserPermissionVo implements Serializable {

    private static final long serialVersionUID = 7046487455365110153L;
    /**
     * 权限实体
     */
    @ApiModelProperty("权限实体")
    private List<SysPermission> sysPermissionList;
}
