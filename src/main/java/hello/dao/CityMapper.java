package hello.dao;

import hello.dto.condition.SearchCityDto;
import hello.entity.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * @param record 用户对象
     * @return 影响的行数
     */
    Integer insertCity(City record);

    /**
     * 通过城市名查询
     *
     * @param name
     * @return
     */
    City searchWithName(String name);

    /**
     * 按条件查询
     *
     * @param searchCityDto
     * @return
     */
    List<City> searchCondition(SearchCityDto searchCityDto);
}