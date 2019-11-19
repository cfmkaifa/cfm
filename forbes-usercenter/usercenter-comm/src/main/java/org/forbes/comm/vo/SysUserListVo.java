package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.forbes.dal.entity.SysUser;

import java.io.Serializable;
import java.util.List;

@ApiModel(description="返回用户列表")
@Data
public class SysUserListVo implements Serializable {

    private static final long serialVersionUID = 7046487455365110153L;
    /**
     * 查询所有用户(未分页)
     */
    private List<SysUser> userList;

}
