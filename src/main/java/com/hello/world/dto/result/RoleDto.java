package com.hello.world.dto.result;

import com.hello.world.entity.Permission;
import com.hello.world.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 角色DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends Role {

  private List<Permission> permissions;
}
