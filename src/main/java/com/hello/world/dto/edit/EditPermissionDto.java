package com.hello.world.dto.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author jarck-lou
 * @date 2019/02/02 14:26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class EditPermissionDto {
  @ApiModelProperty(value = "权限ID", required = true)
  private Long id;

  @ApiModelProperty(value = "权限名称", required = true)
  @NotBlank(message = "权限名称不能为空")
  private String name;

  @ApiModelProperty(value = "权限", required = true)
  @NotBlank(message = "权限不能为空")
  private String permission;

  @ApiModelProperty(value = "权限类型", required = true)
  @NotBlank(message = "权限类型不能为空")
  private String resourceType;
}
