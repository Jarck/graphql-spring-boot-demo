package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.service.IRoleService;
import com.hello.world.entity.City;
import com.hello.world.entity.Company;
import com.hello.world.entity.Role;
import com.hello.world.entity.User;
import com.hello.world.service.ICityService;
import com.hello.world.service.ICompanyService;
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

  public City city(User user) {
    return cityService.searchWithId(user.getCityId());
  }

  public Company company(User user) {
    return companyService.searchWithId(user.getCompanyId());
  }

  public List<Role> roles(User user) {
    return roleService.searchWithUserId(user.getId());
  }
}
