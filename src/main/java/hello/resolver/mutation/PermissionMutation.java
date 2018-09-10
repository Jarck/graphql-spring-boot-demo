package hello.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import hello.dto.create.CreatePermissionDto;
import hello.entity.Permission;
import hello.service.IPermissionService;
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
