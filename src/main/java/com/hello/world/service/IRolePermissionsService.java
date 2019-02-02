package com.hello.world.service;

import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2019/02/01 15:57
 **/
public interface IRolePermissionsService {
  /**
   * 设置角色对应权限
   *
   * @param roleId 角色ID
   * @param permissionIds 权限IDs
   * @return 实际影响行数
   * @throws NotFoundException notFoundException
   */
  int updateRolePermissions(Long roleId, List<Long> permissionIds) throws NotFoundException;
}
