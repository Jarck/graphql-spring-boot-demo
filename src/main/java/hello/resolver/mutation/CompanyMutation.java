package hello.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import hello.dao.CompanyMapper;
import hello.dto.create.CreateCompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/9 21:15
 **/
@Component
public class CompanyMutation implements GraphQLMutationResolver {
  @Autowired
  private CompanyMapper companyMapper;

  public Integer createCompany(CreateCompanyDto createCompanyDto) {
    Integer integer = companyMapper.createCompany(createCompanyDto);

    return integer;
  }
}
