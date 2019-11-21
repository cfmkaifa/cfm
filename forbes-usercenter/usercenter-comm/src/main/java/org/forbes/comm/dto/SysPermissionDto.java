package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysPermission;
import org.forbes.dal.entity.SysRole;
import org.forbes.dal.entity.SysUser;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/***
 * @类描述 权限传入model
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/21
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel(description="权限管理传入对象")
@Data
public class SysPermissionDto implements Serializable{

    private static final long serialVersionUID = 7046487455365110153L;

    @ApiModelProperty("传入权限对象")
    @NotEmpty(message = "权限对象不能为空")
    private SysPermission sysPermission;

}
