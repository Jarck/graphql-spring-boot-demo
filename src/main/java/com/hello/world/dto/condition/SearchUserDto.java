package com.hello.world.dto.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索用户DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户搜索条件")
public class SearchUserDto {
  @ApiModelProperty(value = "用户ID")
  private Long id;

  @ApiModelProperty(value = "用户名")
  private String name;

  @ApiModelProperty(value = "用户手机号码")
  private String phone;

  @ApiModelProperty(value = "角色ID")
  private Long roleId;

  @ApiModelProperty(value = "城市ID")
  private Long cityId;

  @ApiModelProperty(value = "公司ID")
  private Long companyId;

  private Integer status;
}
