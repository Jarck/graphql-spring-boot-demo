package com.hello.world.dao;

import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
public interface RoleMapper {
  /**
   * 按ID删除
   *
   * @param id ID
   * @return 影响行数
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insert(Role record);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insertSelective(Role record);

  /**
   * 按ID查询
   *
   * @param id ID
   * @return 角色
   */
  Role selectByPrimaryKey(Long id);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(Role record);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKey(Role record);

  /**
   * 查询所有角色
   *
   * @return 角色列表
   */
  List<Role> findAll();

  /**
   * 创建角色
   *
   * @param createRoleDto createRoleDto
   * @return 影响的行数
   */
  Long insertRole(CreateRoleDto createRoleDto);

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
  List<Role> searchWithUserId(@Param("userId") Long userId);
}
