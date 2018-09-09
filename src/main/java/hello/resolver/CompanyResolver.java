package hello.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import hello.dao.CityMapper;
import hello.entity.City;
import hello.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:09
 **/
@Component
public class CompanyResolver implements GraphQLResolver<Company> {
  @Autowired
  private CityMapper cityMapper;

  public City city(Company company) {
    return cityMapper.selectByPrimaryKey(company.getCityId());
  }
}
