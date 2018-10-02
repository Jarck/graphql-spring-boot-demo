package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.entity.Company;
import com.hello.world.dto.create.CreateCompanyDto;
import com.hello.world.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/9 21:15
 **/
@Component
public class CompanyMutation implements GraphQLMutationResolver {
  @Autowired
  private ICompanyService companyService;

  /**
   * 新建公司
   *
   * @param createCompanyDto 公司
   * @return 公司
   */
  public Company createCompany(CreateCompanyDto createCompanyDto) {
    Long companyId = companyService.createCompany(createCompanyDto);
    Company company = companyService.searchWithId(companyId);

    return company;
  }
}
