package com.hello.world.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hello.world.enums.PermissionAvailableEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@ApiModel
public class Permission implements Serializable {
  private static final long serialVersionUID = 5645143355384314850L;

  @ApiModelProperty(value = "权限ID")
  private Long id;

  @ApiModelProperty(value = "权限名称")
  private String name;

  @ApiModelProperty(value = "权限")
  private String permission;

  @ApiModelProperty(value = "权限类型")
  private String resourceType;

  @ApiModelProperty(value = "状态")
  private PermissionAvailableEnum available;

  @ApiModelProperty(value = "创建时间")
  @JsonIgnore
  private Date createdAt;

  @ApiModelProperty(value = "更新时间")
  @JsonIgnore
  private Date updatedAt;
}
