package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateCompanyDto;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.exception.GraphQLValidateException;
import com.hello.world.service.ICompanyService;
import com.hello.world.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
   * @throws GraphQLValidateException 参数校验异常
   */
  public CompanyDto createCompany(CreateCompanyDto createCompanyDto) throws GraphQLValidateException {
    // 校验参数
    Map<String, StringBuffer> errorMap = ValidatorUtil.validate(createCompanyDto);
    if (errorMap != null) {
      throw new GraphQLValidateException(errorMap.toString());
    }

    companyService.createCompany(createCompanyDto);
    CompanyDto companyDto = companyService.searchWithId(createCompanyDto.getId());

    return companyDto;
  }
}
