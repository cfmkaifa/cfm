package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.forbes.dal.entity.SysUser;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/***
 * @类描述 角色传入model
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/21
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel("传入用户对象")
@Data
public class SysUserDto implements Serializable{


    private static final long serialVersionUID = 1077950871191386830L;

    @ApiModelProperty("传入用户对象")
    private SysUser sysUser;

}
