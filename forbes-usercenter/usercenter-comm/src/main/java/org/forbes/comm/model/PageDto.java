package org.forbes.comm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import java.io.Serializable;

/***
 * @创建人 niehy(Frunk)
 * @创建时间 2019/11/26
 * @修改人 (修改了该文件，请填上修改人的名字)
 * @修改日期 (请填上修改该文件时的日期)
 */
@ApiModel(description="多条件分页查询传入参数")
@Data
public class PageDto implements Serializable{

    private static final long serialVersionUID = 5528079878333068701L;

    @ApiModelProperty("当前页码")
    @NotNull(message = "当前页码不能为空")
    @DefaultValue(value = "1")
    private Long current;

    @ApiModelProperty("当前页显示条数")
    @NotNull(message = "当前页显示条数不能为空")
    @DefaultValue(value = "10")
    private Long size;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("资源名称")
    private String name;
}