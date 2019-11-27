package org.forbes.comm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/***
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/22
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel("修改角色的一个权限传入参数")
@Data
public class UpdatePermissionToRoleDto  implements Serializable {

    private static final long serialVersionUID = -1361652913559943696L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("主键id，自增不需传值")
    private Long id;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人，不需要传值")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间，不需要传值")
    private Date updateTime;

    /**
     * 角色ID
     * Table:     f_sys_role_permission
     * Column:    role_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**
     * 权限ID
     * Table:     f_sys_role_permission
     * Column:    permission_id
     * Nullable:  true
     */
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;

}
