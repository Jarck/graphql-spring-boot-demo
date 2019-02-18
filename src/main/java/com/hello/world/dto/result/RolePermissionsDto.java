package com.hello.world.dto.result;

import com.hello.world.entity.Permission;
import com.hello.world.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2019/02/15 17:35
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class RolePermissionsDto extends Role {

  @ApiModelProperty(value = "角色对应的权限")
  private List<Permission> permissions;
}
