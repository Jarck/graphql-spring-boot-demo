package com.hello.world.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.dao.CityMapper;
import com.hello.world.dto.condition.SearchCityDto;
import com.hello.world.dto.result.CityDto;
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
    CityDto cityDto = cityMapper.selectByPrimaryKey(cityId);

    return cityDto;
  }

  /**
   * 通过城市名查询
   *
   * @param cityName
   * @return
   */
  @Override
  public List<CityDto> searchWithName(String cityName) {
    List<CityDto> cityList = cityMapper.searchWithName(cityName);

    return cityList;
  }

  /**
   * 按条件查询
   *
   * @param searchCityDto 搜索条件
   * @return
   */
  @Override
  public List<CityDto> searchWithCondition(SearchCityDto searchCityDto) {
    List<CityDto> cityList = cityMapper.searchCondition(searchCityDto);

    return cityList;
  }

  /**
   * 分页查询
   * @param searchCityDto 搜索条件
   * @param pageDto 分页信息
   * @return 城市page
   */
  @Override
  public PageInfo<CityDto> searchWithCondition(SearchCityDto searchCityDto, PageDto pageDto) {
    PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
    PageHelper.orderBy(pageDto.getOrderBy() + " " + (pageDto.isDesc() ? "desc" : "asc"));

    List<CityDto> cityList = cityMapper.searchCondition(searchCityDto);

    PageInfo<CityDto> cityPageInfo = new PageInfo<>(cityList);

    return cityPageInfo;
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
