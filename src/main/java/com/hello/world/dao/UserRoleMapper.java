package com.hello.world.dao;

import com.hello.world.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户角色DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
@CacheConfig(cacheNames = "userRoles")
public interface UserRoleMapper {
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
  int insert(UserRole record);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insertSelective(UserRole record);

  /**
   * 创建用户对应的角色
   *
   * @param userId 用户ID
   * @param roleIds 角色IDs
   * @return 影响行数
   */
  int createUserRoles(@Param("userID") Long userId, @Param("roleIds") List<Long> roleIds);

  /**
   * 按ID查询
   *
   * @param id ID
   * @return 用户角色
   */
  UserRole selectByPrimaryKey(Long id);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(UserRole record);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKey(UserRole record);

  /**
   * 通过用户ID查询
   *
   * @param userId 用户ID
   * @return 用户角色列表
   */
  @Cacheable(key = "#p0")
  List<UserRole> searchWithUserId(Long userId);
}
