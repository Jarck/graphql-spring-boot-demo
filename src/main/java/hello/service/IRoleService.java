package hello.service;

import hello.dto.create.CreateRoleDto;
import hello.entity.Role;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 13:38
 **/
public interface IRoleService {

  /**
   * find all roles
   *
   * @return
   */
  List<Role> findAll();

  /**
   * 通过角色id查询
   *
   * @param roleId
   * @return
   */
  Role searchWithId(Long roleId);

  /**
   * 通过角色名查询
   *
   * @param name
   * @return
   */
  List<Role> searchWithName(String name);

  /**
   * 通过用户id查询
   *
   * @param userId
   * @return
   */
  List<Role> searchWithUserId(Long userId);

  /**
   * 创建角色
   *
   * @param createRoleDto
   * @return
   */
  Integer createRole(CreateRoleDto createRoleDto);
}
