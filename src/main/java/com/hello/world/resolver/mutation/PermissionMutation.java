package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.entity.Permission;
import com.hello.world.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:16
 **/
@Component
public class PermissionMutation implements GraphQLMutationResolver {
  @Autowired
  private IPermissionService permissionService;

  public Permission createPermission(CreatePermissionDto createPermissionDto) {
    Long permission_id = permissionService.insertPermission(createPermissionDto);
    Permission permission = permissionService.searchWithId(permission_id);

    return permission;
  }
}
