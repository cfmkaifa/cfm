package org.forbes.dal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import org.forbes.comm.constant.UpdateValid;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class BaseEntity implements Serializable  {

	private static final long serialVersionUID = -5526949198055162338L;


	/**
     * id
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message="主键ID为空",groups=UpdateValid.class)
    private Long id;
    
    
    /**
     * 创建人
     */
    @ApiModelProperty("前端不传值")
    private String createBy;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty("前端不传值")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty("前端不传值")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("前端不传值")
    private Date updateTime;
}
