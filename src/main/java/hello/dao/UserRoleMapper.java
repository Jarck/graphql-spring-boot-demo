package hello.dao;

import hello.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
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
  int deleteByPrimaryKey(Long id);

  int insert(UserRole record);

  int insertSelective(UserRole record);

  UserRole selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(UserRole record);

  int updateByPrimaryKey(UserRole record);

  @Cacheable(key = "#p0")
  List<UserRole> selectByUserId(Long userId);
}