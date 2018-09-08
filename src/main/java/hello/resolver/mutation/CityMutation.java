package hello.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import hello.dto.condition.CreateCityDto;
import hello.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
