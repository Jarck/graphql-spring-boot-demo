package com.hello.world.entity;

import com.hello.world.enums.RoleStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@ApiModel
public class Role implements Serializable {
  private static final long serialVersionUID = 1048618595675804552L;

  @ApiModelProperty(value = "角色ID")
  private Long id;

  @ApiModelProperty(value = "角色名称")
  private String name;

  @ApiModelProperty(value = "状态")
  private RoleStatusEnum status;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "创建时间")
  private Date createdAt;

  @ApiModelProperty(value = "更新时间")
  private Date updatedAt;
}
