package com.hello.world.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.entity.Role;
import com.hello.world.exception.GraphQLNotFoundException;
import com.hello.world.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:09
 **/
@Component
public class RoleQuery implements GraphQLQueryResolver {
  @Autowired
  private IRoleService roleService;

  /**
   * 通过ID查询角色
   *
   * @param roleId 角色ID
   * @return 角色
   */
  public Role searchRoleWithId(Long roleId) {
    Role role = roleService.searchWithId(roleId);

    if (role == null) {
      throw new GraphQLNotFoundException("Not found role with id " + roleId);
    }
    return role;
  }

  /**
   * 通过角色名查询
   *
   * @param name 角色名
   * @return 角色列表
   */
  public List<Role> searchRoleWithName(String name) {
    return roleService.searchWithName(name);
  }

  /**
   * 通过用户ID查询
   *
   * @param userId 用户ID
   * @return 角色列表
   */
  public List<Role> searchRoleWithUserId(Long userId) {
    return roleService.searchWithUserId(userId);
  }
}
