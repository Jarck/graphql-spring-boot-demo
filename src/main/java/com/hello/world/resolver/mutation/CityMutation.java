package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.dto.result.CityDto;
import com.hello.world.exception.ArgumentsException;
import com.hello.world.exception.GraphQLValidateException;
import com.hello.world.service.ICityService;
import com.hello.world.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
   * @throws GraphQLValidateException 参数异常
   * @throws ArgumentsException 参数异常
   */
  public CityDto createCity(CreateCityDto createCityDto) throws GraphQLValidateException, ArgumentsException {
    // 校验参数
    Map<String, StringBuffer> errorMap = ValidatorUtil.validate(createCityDto);
    if (errorMap != null) {
      throw new GraphQLValidateException(errorMap.toString());
    }

    CityDto cityDto = cityService.createCity(createCityDto);

    return cityDto;
  }
}
