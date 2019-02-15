package com.hello.world.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@ApiModel
public class City implements Serializable {
  private static final long serialVersionUID = 4410555123881138923L;

  @ApiModelProperty(value = "城市ID")
  private Long id;

  @ApiModelProperty(value = "城市名称")
  private String name;

  @ApiModelProperty(value = "创建时间")
  @JsonIgnore
  private Date createdAt;

  @ApiModelProperty(value = "更新时间")
  @JsonIgnore
  private Date updatedAt;
}
