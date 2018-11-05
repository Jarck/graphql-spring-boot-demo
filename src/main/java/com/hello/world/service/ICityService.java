package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.dto.condition.SearchCityDto;
import com.hello.world.dto.result.CityDto;
import com.hello.world.entity.City;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
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
   * @param cityName 城市名
   * @return 城市列表
   */
  List<City> searchWithName(String cityName);

  /**
   * 按条件查询
   *
   * @param searchCityDto 搜索条件
   * @return 城市列表
   */
  List<City> searchWithCondition(SearchCityDto searchCityDto);

  /**
   * 分页查询
   * @param searchCityDto 搜索条件
   * @param pageDto 分页信息
   * @return 城市page
   */
  PageInfo<City> searchWithCondition(SearchCityDto searchCityDto, PageDto pageDto);

  /**
   * 创建城市
   *
   * @param createCityDto 城市
   * @return 城市列表
   */
  Long createCity(CreateCityDto createCityDto);
}
