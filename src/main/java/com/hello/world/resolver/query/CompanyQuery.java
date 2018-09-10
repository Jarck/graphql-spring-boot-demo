package com.hello.world.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.entity.Company;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 21:03
 **/
@Component
public class CompanyQuery implements GraphQLQueryResolver {
  @Autowired
  private ICompanyService companyService;

  /**
   * 通过查询条件查询公司
   *
   * @param searchCompanyDto
   * @return
   */
  public List<Company> searchCompanies(SearchCompanyDto searchCompanyDto) {
    return companyService.searchCondition(searchCompanyDto);
  }

  /**
   * 通过ID查询公司
   *
   * @param companyId
   * @return
   */
  public Company searchCompanyWithId(Long companyId) {
    return companyService.searchWithId(companyId);
  }

  /**
   * 通过城市ID查询公司
   * @param cityId
   * @return
   */
  public List<Company> searchCompanyWithCityId(Long cityId) {
    return companyService.searchWithCityId(cityId);
  }

  /**
   * 通过公司名查询
   *
   * @param name
   * @return
   */
  public List<Company> searchCompanyWithName(String name) {
    return companyService.searchWithName(name);
  }
}
