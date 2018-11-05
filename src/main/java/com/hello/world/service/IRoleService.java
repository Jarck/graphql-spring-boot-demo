package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.entity.Role;

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
  List<Role> findAll();

  /**
   * 分页查询
   * @param pageDto 分页信息
   * @return 角色id
   */
  PageInfo<Role> findAll(PageDto pageDto);

  /**
   * 通过角色id查询
   *
   * @param roleId 角色ID
   * @return 角色
   */
  Role searchWithId(Long roleId);

  /**
   * 通过角色名查询
   *
   * @param name 角色名
   * @return 角色列表
   */
  List<Role> searchWithName(String name);

  /**
   * 通过用户ID查询
   *
   * @param userId 用户ID
   * @return 角色列表
   */
  List<Role> searchWithUserId(Long userId);

  /**
   * 创建角色
   *
   * @param createRoleDto 角色
   * @return 影响行数
   */
  Long createRole(CreateRoleDto createRoleDto);
}
