package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.dto.result.PermissionDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:52
 **/
@Component
public class RoleResolver implements GraphQLResolver<RoleDto> {
  @Autowired
  private IPermissionService permissionService;

  /**
   * 按角色查询权限
   *
   * @param roleDto 角色
   * @return 权限列表
   */
  public List<PermissionDto> permissions(RoleDto roleDto) {
    return permissionService.searchWithRoleId(roleDto.getId());
  }
}
