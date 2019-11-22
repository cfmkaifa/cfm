package org.forbes.comm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName
 * @Description 关于数据库写操作公共vo
 * @Author
 * @Date 2019/11/22 18:08
 * @Version 1.0
 **/
@ApiModel(description = "公共vo")
@Data
public class CommVo implements Serializable{

    @ApiModelProperty("封装map")
    private Map<String,Boolean> mapInfo;
}
