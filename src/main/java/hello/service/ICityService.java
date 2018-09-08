package hello.service;

import hello.dto.condition.CreateCityDto;
import hello.dto.condition.SearchCityDto;
import hello.dto.result.CityDto;
import hello.entity.City;

import java.util.List;


public interface ICityService {
    /**
     * find by id
     *
     * @param cityId city id
     * @return cityDto
     */
    CityDto searchWithId(Long cityId);

    /**
     * 通过城市名查询
     *
     * @param cityName
     * @return
     */
    CityDto searchWithName(String cityName);

    /**
     * 按条件查询
     *
     * @param searchCityDto 搜索条件
     * @return
     */
    List<City> searchWithCondition(SearchCityDto searchCityDto);

    /**
     * 创建城市
     *
     * @param createCityDto
     * @return
     */
    String createCity(CreateCityDto createCityDto);
}
