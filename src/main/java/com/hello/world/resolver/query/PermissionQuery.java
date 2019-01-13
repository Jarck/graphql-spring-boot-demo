package com.hello.world.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.dto.result.PermissionDto;
import com.hello.world.exception.GraphQLNotFoundException;
import com.hello.world.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/10 09:36
 **/
@Component
public class PermissionQuery implements GraphQLQueryResolver {
  @Autowired
  private IPermissionService permissionService;

  /**
   * 通过ID查询权限
   *
   * @param permissionId 权限ID
   * @return 权限
   */
  public PermissionDto searchPermissionWithId(Long permissionId) {
    PermissionDto permissionDto = permissionService.searchWithId(permissionId);

    if (permissionDto == null) {
      throw new GraphQLNotFoundException("Not found permission with id " + permissionId);
    }

    return permissionDto;
  }

  /**
   * 通过权限名查询
   *
   * @param name 权限名
   * @return 权限列表
   */
  public List<PermissionDto> searchPermissionWithName(String name) {
    return permissionService.searchWithName(name);
  }

  /**
   * 通过角色ID查询
   *
   * @param roleId 角色ID
   * @return 权限列表
   */
  public List<PermissionDto> searchPermissionWithRoleId(Long roleId) {
    return permissionService.searchWithRoleId(roleId);
  }

  /**
   * 通过用户ID查询
   *
   * @param userId 用户ID
   * @return 权限列表
   */
  public List<PermissionDto> searchPermissionWithUserId(Long userId) {
    return permissionService.searchWithUserId(userId);
  }
}
