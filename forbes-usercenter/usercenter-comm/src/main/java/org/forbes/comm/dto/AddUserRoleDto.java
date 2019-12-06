package org.forbes.comm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName
 * @Description TODO
 * @Author
 * @Date 2019/11/21 18:10
 * @Version 1.0
 **/
@Data
@ApiModel("添加用户角色中间表")
public class AddUserRoleDto implements Serializable{

    private static final long serialVersionUID = 5601288102119484469L;

    @ApiModelProperty(value = "用户id")
    @NotEmpty(message = "用户id不能为空")
    private Long userId;


    @ApiModelProperty(value = "角色id")
    @NotEmpty(message = "角色id不能为空")
    private Long roleId;

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

}
