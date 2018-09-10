package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.entity.Role;
import com.hello.world.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:13
 **/
@Component
public class RoleMutation implements GraphQLMutationResolver {
  @Autowired
  private IRoleService roleService;

  /**
   * 新建角色
   *
   * @param createRoleDto
   * @return
   */
  public Role createRole(CreateRoleDto createRoleDto) {
    Long role_id = roleService.createRole(createRoleDto);
    Role role = roleService.searchWithId(role_id);

    return role;
  }
}
