package org.forbes.comm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/20 14:19
 * @Version 1.0
 **/
@Data
@ApiModel(description = "角色集合")
public class RoleVo implements Serializable{

    private static final long serialVersionUID = -3657719596986178689L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Long id;


    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 角色名称
     * Table:     f_sys_role
     * Column:    role_name
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 角色编码
     * Table:     f_sys_role
     * Column:    role_code
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;

    /**
     * 角色描述
     * Table:     f_sys_role
     * Column:    description
     * Nullable:  true
     */
    @ApiModelProperty(value = "角色描述")
    private String description;
}
