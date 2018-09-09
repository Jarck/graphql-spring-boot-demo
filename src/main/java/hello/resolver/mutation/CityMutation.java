package hello.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import hello.dto.create.CreateCityDto;
import hello.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 城市Mutations
 *
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
  public String createCity(CreateCityDto input) {
    return cityService.createCity(input);
  }
}
