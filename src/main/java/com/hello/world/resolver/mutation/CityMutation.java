package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.entity.City;
import com.hello.world.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class CityMutation implements GraphQLMutationResolver {
  @Autowired
  private ICityService cityService;

  /**
   * 创建城市
   *
   * @param createCityDto 城市
   * @return 城市
   */
  public City createCity(CreateCityDto createCityDto) {
    cityService.createCity(createCityDto);
    City city = cityService.searchWithId(createCityDto.getId());

    return city;
  }
}
