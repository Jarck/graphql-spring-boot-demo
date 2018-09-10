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
   * @param input
   * @return
   */
  public City createCity(CreateCityDto input) {
    Long city_id = cityService.createCity(input);
    City city = cityService.searchWithId(city_id);

    return city;
  }
}