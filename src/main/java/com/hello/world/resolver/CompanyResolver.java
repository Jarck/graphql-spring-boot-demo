package com.hello.world.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.hello.world.dto.result.CityDto;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:09
 **/
@Component
public class CompanyResolver implements GraphQLResolver<CompanyDto> {
  @Autowired
  private ICityService cityService;

  /**
   * 按公司查找城市
   *
   * @param companyDto 公司
   * @return 城市
   */
  public CityDto city(CompanyDto companyDto) {
    return cityService.searchWithId(companyDto.getCityId());
  }
}
