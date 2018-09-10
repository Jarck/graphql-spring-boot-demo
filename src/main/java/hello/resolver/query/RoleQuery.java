package hello.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import hello.entity.Role;
import hello.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/10 10:09
 **/
@Component
public class RoleQuery implements GraphQLQueryResolver {
  @Autowired
  private IRoleService roleService;

  /**
   * 通过ID查询角色
   *
   * @param roleId
   * @return
   */
  public Role searchRoleWithId(Long roleId) {
    return roleService.searchWithId(roleId);
  }

  /**
   * 通过角色名查询
   *
   * @param name
   * @return
   */
  public List<Role> searchRoleWithName(String name) {
    return roleService.searchWithName(name);
  }

  /**
   * 通过用户ID查询
   *
   * @param userId
   * @return
   */
  public List<Role> searchRoleWithUserId(Long userId) {
    return roleService.searchWithUserId(userId);
  }
}
