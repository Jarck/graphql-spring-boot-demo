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
  int deleteByPrimaryKey(Long id);

  int insert(RolePermission record);

  int insertSelective(RolePermission record);

  RolePermission selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(RolePermission record);

  int updateByPrimaryKey(RolePermission record);
}