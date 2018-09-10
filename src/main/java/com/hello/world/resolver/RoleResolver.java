package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.entity.Permission;
import com.hello.world.entity.Role;
import com.hello.world.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:52
 **/
@Component
public class RoleResolver implements GraphQLResolver<Role> {
  @Autowired
  private IPermissionService permissionService;

  public List<Permission> permissions(Role role) {
    return permissionService.searchWithRoleId(role.getId());
  }
}
