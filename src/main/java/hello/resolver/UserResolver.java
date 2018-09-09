package hello.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import hello.entity.City;
import hello.entity.Company;
import hello.entity.User;
import hello.service.ICityService;
import hello.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class UserResolver implements GraphQLResolver<User> {
  @Autowired
  private ICityService cityService;

  @Autowired
  private ICompanyService companyService;

  public City city(User user) {
    return cityService.searchWithId(user.getCityId());
  }

  public Company company(User user) {
    return companyService.searchWithId(user.getCompanyId());
  }
}
