package hello.dao;

import hello.dto.create.CreateRoleDto;
import hello.entity.Role;
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
  int deleteByPrimaryKey(Long id);

  int insert(Role record);

  int insertSelective(Role record);

  Role selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(Role record);

  int updateByPrimaryKey(Role record);

  Integer insertRole(CreateRoleDto createRoleDto);

  List<Role> findAll();

  List<Role> searchWithName(String name);

  List<Role> searchWithUserId(@Param("userId") Long userId);
}