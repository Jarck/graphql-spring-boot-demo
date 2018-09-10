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
  int deleteByPrimaryKey(Long id);

  int insert(Permission record);

  int insertSelective(Permission record);

  Permission selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(Permission record);

  int updateByPrimaryKey(Permission record);

  /**
   * 创建权限
   *
   * @param createPermissionDto
   * @return 影响的行数
   */
  Long insertPermission(CreatePermissionDto createPermissionDto);

  /**
   * 按权限名查询
   *
   * @param name
   * @return
   */
  List<Permission> searchWithName(String name);

  /**
   * 按角色ID查询
   *
   * @param roleId
   * @return
   */
  List<Permission> searchWithRoleId(@Param("roleId") Long roleId);

  /**
   * 按用户ID查询
   *
   * @param userId
   * @return
   */
  List<Permission> searchWithUserId(@Param("userId") Long userId);
}