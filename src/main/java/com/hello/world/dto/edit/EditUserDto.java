package com.hello.world.dto.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author jarck-lou
 * @date 2019/01/28 15:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class EditUserDto {
  @ApiModelProperty(value = "用户ID", required = true)
  private Long id;

  @ApiModelProperty(value = "姓名", required = true)
  @NotBlank(message = "姓名不能为空")
  private String name;

  @ApiModelProperty(value = "手机号码", required = true)
  @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
  @NotBlank(message = "手机号码不能为空")
  private String phone;

  @ApiModelProperty(value = "城市ID")
  private Long cityId;

  @ApiModelProperty(value = "公司ID")
  private Long companyId;

  @ApiModelProperty(value = "角色IDs", example = "[1, 2]")
  private Long[] roleIds;
}
