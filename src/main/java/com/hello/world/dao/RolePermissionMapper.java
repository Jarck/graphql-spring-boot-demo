package com.hello.world.dao;

import com.hello.world.entity.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
public interface RolePermissionMapper {
  /**
   * 按ID删除
   *
   * @param id id
   * @return 影响行数
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 按角色ID删除
   *
   * @param roleId 角色ID
   * @return 影响行数
   */
  @Delete("DELETE * FROM role_permission WHERE role_id = #{roleId}")
  int deleteByRoleId(Long roleId);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insert(RolePermission record);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insertSelective(RolePermission record);

  /**
   * 创建角色对应权限
   *
   * @param roleId 角色ID
   * @param permissionIds 权限IDs
   * @return 影响行数
   */
  int createRolePermissions(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds);

  /**
   * 按ID查询
   *
   * @param id ID
   * @return 角色权限
   */
  RolePermission selectByPrimaryKey(Long id);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(RolePermission record);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKey(RolePermission record);
}
