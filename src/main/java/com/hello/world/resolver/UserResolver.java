package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.entity.City;
import com.hello.world.entity.Company;
import com.hello.world.entity.Role;
import com.hello.world.entity.User;
import com.hello.world.service.ICityService;
import com.hello.world.service.ICompanyService;
import com.hello.world.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class UserResolver implements GraphQLResolver<User> {
  @Autowired
  private ICityService cityService;

  @Autowired
  private ICompanyService companyService;

  @Autowired
  private IRoleService roleService;

  /**
   * 按用户查询城市
   *
   * @param user user
   * @return 城市
   */
  public City city(User user) {
    return cityService.searchWithId(user.getCityId());
  }

  /**
   * 按用户查询公司
   *
   * @param user user
   * @return 公司
   */
  public Company company(User user) {
    return companyService.searchWithId(user.getCompanyId());
  }

  /**
   * 角色列表
   *
   * @param user user
   * @return 角色列表
   */
  public List<Role> roles(User user) {
    return roleService.searchWithUserId(user.getId());
  }
}
