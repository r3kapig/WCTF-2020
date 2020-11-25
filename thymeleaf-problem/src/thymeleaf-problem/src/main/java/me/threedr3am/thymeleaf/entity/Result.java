package me.threedr3am.thymeleaf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@Builder
public class Result<T> implements Serializable {

    @ApiModelProperty("业务响应数据")
    private T result;
    private boolean success;
    @ApiModelProperty("业务响应信息")
    private String message;
    @ApiModelProperty("业务响应码")
    private Integer code;
}

