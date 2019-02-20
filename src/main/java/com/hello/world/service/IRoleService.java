package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.edit.EditRoleDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.dto.result.RolePermissionsDto;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 13:38
 **/
public interface IRoleService {

  /**
   * find all roles
   *
   * @return 角色列表
   */
  List<RoleDto> findAll();

  /**
   * 分页查询
   * @param pageDto 分页信息
   * @return 角色
   */
  PageInfo<RoleDto> findAll(PageDto pageDto);

  /**
   * 通过角色ID查询
   *
   * @param roleId 角色ID
   * @return 角色
   */
  RoleDto searchWithId(Long roleId);

  /**
   * 通过角色ID查询
   *
   * @param roleId 角色ID
   * @return 角色
   */
  RolePermissionsDto searchRoleAndPermissions(Long roleId);

  /**
   * 通过角色名查询
   *
   * @param name 角色名
   * @return 角色列表
   */
  List<RoleDto> searchWithName(String name);

  /**
   * 通过用户ID查询
   *
   * @param userId 用户ID
   * @return 角色列表
   */
  List<RoleDto> searchWithUserId(Long userId);

  /**
   * 创建角色
   *
   * @param createRoleDto 角色
   * @return 影响行数
   */
  RoleDto createRole(CreateRoleDto createRoleDto);

  /**
   * 更新角色
   *
   * @param editRoleDto 角色
   * @return 角色
   */
  RoleDto updateRole(EditRoleDto editRoleDto);

  /**
   * 判断角色名称是否存在
   *
   * @param name 角色名称
   * @return boolean
   */
  boolean exitsRoleName(String name);
}
