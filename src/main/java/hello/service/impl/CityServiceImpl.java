package hello.service.impl;

import hello.constant.SystemConstant;
import hello.dao.CityMapper;
import hello.dto.condition.CreateCityDto;
import hello.dto.condition.SearchCityDto;
import hello.dto.result.CityDto;
import hello.entity.City;
import hello.service.ICityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private CityMapper cityMapper;

    /**
     * find by id
     *
     * @param cityId city id
     * @return cityDto
     */
    @Override
    public CityDto searchWithId(Long cityId) {
        City city = cityMapper.selectByPrimaryKey(cityId);
        CityDto cityDto = new CityDto(city);

        return cityDto;
    }

    /**
     * 通过城市名查询
     *
     * @param cityName
     * @return
     */
    @Override
    public CityDto searchWithName(String cityName) {
        City city = cityMapper.searchWithName(cityName);
        CityDto cityDto = new CityDto(city);

        return cityDto;
    }

    /**
     * 按条件查询
     *
     * @param searchCityDto 搜索条件
     * @return
     */
    @Override
    public List<City> searchWithCondition(SearchCityDto searchCityDto) {
        List<City> cities = cityMapper.searchCondition(searchCityDto);

        return cities;
    }

    /**
     * 创建城市
     *
     * @param createCityDto
     * @return
     */
    @Override
    public String createCity(CreateCityDto createCityDto) {
        try {
            City city = new City();
            city.setName(createCityDto.getName());

            cityMapper.insertCity(city);
        } catch (Exception e) {
            log.error("Create city error", e);
            return SystemConstant.RETURN_ERROR;
        }

        return SystemConstant.RETURN_SUCCESS;
    }
}
