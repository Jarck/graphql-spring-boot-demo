package com.hello.world.dao;

import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
public interface PermissionMapper {
  /**
   * 按ID删除
   *
   * @param id id
   * @return 影响行数
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insert(Permission record);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insertSelective(Permission record);

  /**
   * 按ID查询
   *
   * @param id id
   * @return 权限
   */
  Permission selectByPrimaryKey(Long id);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(Permission record);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKey(Permission record);

  /**
   * 创建权限
   *
   * @param createPermissionDto createPermissionDto
   * @return 影响的行数
   */
  Long insertPermission(CreatePermissionDto createPermissionDto);

  /**
   * 按权限名查询
   *
   * @param name 权限名
   * @return 权限列表
   */
  List<Permission> searchWithName(String name);

  /**
   * 按角色ID查询
   *
   * @param roleId 角色ID
   * @return 权限列表
   */
  List<Permission> searchWithRoleId(@Param("roleId") Long roleId);

  /**
   * 按用户ID查询
   *
   * @param userId 用户ID
   * @return 权限列表
   */
  List<Permission> searchWithUserId(@Param("userId") Long userId);
}
