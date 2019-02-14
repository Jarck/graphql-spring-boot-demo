package com.hello.world.entity;

import com.hello.world.enums.UserStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@ApiModel
public class User implements Serializable {
  private static final long serialVersionUID = 6365954110010810033L;

  @ApiModelProperty(value = "用户ID")
  private Long id;

  @ApiModelProperty(value = "用户名称")
  private String name;

  @ApiModelProperty(value = "手机号码")
  private String phone;

  @ApiModelProperty(value = "城市ID")
  private Long cityId;

  @ApiModelProperty(value = "公司ID")
  private Long companyId;

  @ApiModelProperty(value = "状态")
  private UserStatusEnum status;

  @ApiModelProperty(value = "创建时间")
  private Date createdAt;

  @ApiModelProperty(value = "更新时间")
  private Date updatedAt;
}
