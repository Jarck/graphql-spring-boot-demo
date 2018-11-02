package com.hello.world.service.impl;

import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.dao.CityMapper;
import com.hello.world.dto.condition.SearchCityDto;
import com.hello.world.dto.result.CityDto;
import com.hello.world.entity.City;
import com.hello.world.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
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
    CityDto cityDto = null;

    if (city != null) {
      cityDto = new CityDto(city);
    }

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
  public Long createCity(CreateCityDto createCityDto) {
    return cityMapper.insertCity(createCityDto);
  }
}
