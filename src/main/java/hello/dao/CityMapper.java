package hello.dao;

import hello.dto.condition.SearchCityDto;
import hello.dto.create.CreateCityDto;
import hello.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 城市DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
public interface CityMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(City record);

  int insertSelective(City record);

  City selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(City record);

  int updateByPrimaryKey(City record);

  /**
   * 创建城市
   *
   * @param createCityDto
   * @return 影响的行数
   */
  Long insertCity(CreateCityDto createCityDto);

  /**
   * 通过城市名查询
   *
   * @param name
   * @return
   */
  List<City> searchWithName(String name);

  /**
   * 按条件查询
   *
   * @param searchCityDto
   * @return
   */
  List<City> searchCondition(SearchCityDto searchCityDto);
}