package com.hello.world.dao;

import com.hello.world.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
