package com.hello.world.dto.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;


/**
 * @author jarck-lou
 * @date 2019/02/01 15:33
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class EditRoleDto {
  @ApiModelProperty(value = "角色ID", required = true)
  private Long id;

  @ApiModelProperty(value = "角色名称", required = true)
  @NotBlank(message = "角色名称不能为空")
  private String name;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "权限IDs", example = "[1, 2]")
  private List<Long> permissionIds;
}
