package hello.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import hello.dto.condition.SearchCityDto;
import hello.entity.City;
import hello.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityQuery implements GraphQLQueryResolver {
    @Autowired
    private ICityService cityService;

    /**
     * 通过查询条件查询城市
     *
     * @param input
     * @return
     */
    public List<City> searchCities(SearchCityDto input) {
        List<City> cities = cityService.searchWithCondition(input);

        return cities;
    }

    /**
     * 通过ID查询城市
     *
     * @param cityId
     * @return
     */
    public City getCityWithId(Long cityId) {
        City city = cityService.searchWithId(cityId);

        return city;
    }

    /**
     * 通过城市名查询
     *
     * @param cityName
     * @return
     */
    public City getCityWithName(String cityName) {
        City city = cityService.searchWithName(cityName);

        return city;
    }
}
