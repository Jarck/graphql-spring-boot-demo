package hello.service.impl;

import hello.constant.SystemConstant;
import hello.dao.CityMapper;
import hello.dto.condition.SearchCityDto;
import hello.dto.create.CreateCityDto;
import hello.dto.result.CityDto;
import hello.entity.City;
import hello.service.ICityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
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
  public List<City> searchWithName(String cityName) {
    List<City> cityList = cityMapper.searchWithName(cityName);

    return cityList;
  }

  /**
   * 按条件查询
   *
   * @param searchCityDto 搜索条件
   * @return
   */
  @Override
  public List<City> searchWithCondition(SearchCityDto searchCityDto) {
    List<City> cityList = cityMapper.searchCondition(searchCityDto);

    return cityList;
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
