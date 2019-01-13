package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.dto.result.CityDto;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.dto.result.UserDto;
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
public class UserResolver implements GraphQLResolver<UserDto> {
  @Autowired
  private ICityService cityService;

  @Autowired
  private ICompanyService companyService;

  @Autowired
  private IRoleService roleService;

  /**
   * 按用户查询城市
   *
   * @param userDto userDto
   * @return 城市
   */
  public CityDto city(UserDto userDto) {
    return cityService.searchWithId(userDto.getCityId());
  }

  /**
   * 按用户查询公司
   *
   * @param userDto userDto
   * @return 公司
   */
  public CompanyDto company(UserDto userDto) {
    return companyService.searchWithId(userDto.getCompanyId());
  }

  /**
   * 角色列表
   *
   * @param userDto userDto
   * @return 角色列表
   */
  public List<RoleDto> roles(UserDto userDto) {
    return roleService.searchWithUserId(userDto.getId());
  }
}
