package hello.dao;

import hello.entity.Permission;
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

  List<Permission> findByRoleId(@Param("roleId") Long roleId);

  List<Permission> findByUserId(@Param("userId") Long userId);
}