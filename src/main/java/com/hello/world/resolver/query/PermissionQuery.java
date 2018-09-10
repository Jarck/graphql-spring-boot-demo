package com.hello.world.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.entity.Permission;
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
   * @param permissionId
   * @return
   */
  public Permission searchPermissionWithId(Long permissionId) {
    return permissionService.searchWithId(permissionId);
  }

  /**
   * 通过权限名查询
   *
   * @param name
   * @return
   */
  public List<Permission> searchPermissionWithName(String name) {
    return permissionService.searchWithName(name);
  }

  /**
   * 通过角色ID查询
   *
   * @param roleId
   * @return
   */
  public List<Permission> searchPermissionWithRoleId(Long roleId) {
    return permissionService.searchWithRoleId(roleId);
  }

  /**
   * 通过用户ID查询
   *
   * @param userId
   * @return
   */
  public List<Permission> searchPermissionWithUserId(Long userId) {
    return permissionService.searchWithUserId(userId);
  }
}
