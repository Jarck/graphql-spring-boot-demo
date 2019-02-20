package com.hello.world.service;

import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.edit.EditPermissionDto;
import com.hello.world.dto.result.PermissionDto;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 20:01
 **/
public interface IPermissionService {

  /**
   * find all permissions
   *
   * @return 权限列表
   */
  List<PermissionDto> findAll();

  /**
   * find by id
   *
   * @param permissionId 权限ID
   * @return 权限
   */
  PermissionDto searchWithId(Long permissionId);

  /**
   * 通过权限名查询
   *
   * @param name 权限名称
   * @return 权限列表
   */
  List<PermissionDto> searchWithName(String name);

  /**
   * 通过角色id查询
   *
   * @param roleId 角色ID
   * @return 权限列表
   */
  List<PermissionDto> searchWithRoleId(Long roleId);

  /**
   * 通过用户id查询
   *
   * @param userId 用户ID
   * @return 权限列表
   */
  List<PermissionDto> searchWithUserId(Long userId);

  /**
   * 创建权限
   *
   * @param createPermissionDto 权限
   * @return 影响行数
   */
  PermissionDto createPermission(CreatePermissionDto createPermissionDto);

  /**
   * 更新权限
   *
   * @param editPermissionDto 权限
   * @return 权限
   * @throws NotFoundException notFoundException
   */
  PermissionDto updatePermission(EditPermissionDto editPermissionDto) throws NotFoundException;

  /**
   * 判断权限名称是否存在
   *
   * @param name 权限名称
   * @return boolean
   */
  boolean exitsPermissionName(String name);
}
