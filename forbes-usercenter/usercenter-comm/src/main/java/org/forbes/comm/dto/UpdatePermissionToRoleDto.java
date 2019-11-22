package org.forbes.comm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 * @类描述 权限传入model
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/22
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel("传入用户对象")
@Data
public class UpdatePermissionToRoleDto  implements Serializable {

    @ApiModelProperty(value="权限id集合")
    private List<Integer> PermissionIdList;
}
