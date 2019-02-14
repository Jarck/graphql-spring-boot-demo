package com.hello.world.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author jarck-lou
 * @date 2018/9/9 14:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {
  private Long id;

  @NotBlank(message = "角色名称不能为空")
  private String name;

  private String remark;
}
