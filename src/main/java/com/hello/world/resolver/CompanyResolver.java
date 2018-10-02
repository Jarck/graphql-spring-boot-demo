package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.dao.CityMapper;
import com.hello.world.entity.City;
import com.hello.world.entity.Company;
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

  /**
   * 按公司查找城市
   *
   * @param company 公司
   * @return 城市
   */
  public City city(Company company) {
    return cityMapper.selectByPrimaryKey(company.getCityId());
  }
}
