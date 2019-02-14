package com.hello.world.web.rest;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @param <T> 泛型
 * @author jarck-lou
 * @date 2018/11/07 10:03
 */
@Data
@AllArgsConstructor
@ApiModel
public class ResponseBean<T> {
  @ApiModelProperty(value = "返回状态码", required = true)
  private int code;

  @ApiModelProperty(value = "返回信息", required = true)
  private String msg;

  @ApiModelProperty(value = "返回数据")
  private T data;
}
