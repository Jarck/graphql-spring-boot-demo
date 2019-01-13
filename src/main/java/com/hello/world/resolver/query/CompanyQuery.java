package com.hello.world.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.exception.GraphQLNotFoundException;
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
   * @param searchCompanyDto 搜索条件
   * @return 公司列表
   */
  public List<CompanyDto> searchCompanies(SearchCompanyDto searchCompanyDto) {
    return companyService.searchCondition(searchCompanyDto);
  }

  /**
   * 通过ID查询公司
   *
   * @param companyId 公司ID
   * @return 公司
   */
  public CompanyDto searchCompanyWithId(Long companyId) {
    CompanyDto companyDto = companyService.searchWithId(companyId);

    if (companyDto == null) {
      throw new GraphQLNotFoundException("Not found company with id " + companyId);
    }

    return companyDto;
  }

  /**
   * 通过城市ID查询公司
   * @param cityId 公司ID
   * @return 公司列表
   */
  public List<CompanyDto> searchCompanyWithCityId(Long cityId) {
    return companyService.searchWithCityId(cityId);
  }

  /**
   * 通过公司名查询
   *
   * @param name 公司名称
   * @return 公司列表
   */
  public List<CompanyDto> searchCompanyWithName(String name) {
    return companyService.searchWithName(name);
  }
}
