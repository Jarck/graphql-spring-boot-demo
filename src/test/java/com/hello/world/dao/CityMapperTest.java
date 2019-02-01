package com.hello.world.dao;

import com.hello.world.dto.condition.SearchCityDto;
import com.hello.world.dto.create.CreateCityDto;
import com.hello.world.dto.result.CityDto;
import com.hello.world.entity.City;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/14 09:38
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@MybatisTest
@Transactional
public class CityMapperTest {
  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Autowired
  private CityMapper cityMapper;

  @Resource
  private DataSource dataSource;

  @Before
  public void setUp() throws Exception {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("city"),
            Operations.insertInto("city")
                      .columns("id", "name")
                      .values(1, "杭州")
                      .values(2, "苏州")
                      .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  public void testSelectByPrimaryKey() {
    City city = cityMapper.selectByPrimaryKey(1L);

    Assert.assertEquals(city.getName(), "杭州");
  }

  @Test
  public void testInsert() {
    City city = insertCity();

    City city_insert = cityMapper.selectByPrimaryKey(3L);
    Assert.assertEquals(city_insert.getId(), city.getId());
    Assert.assertEquals(city_insert.getName(), city.getName());
  }

  @Test
  public void testInsertCity() {
    CreateCityDto createCityDto = new CreateCityDto();
    createCityDto.setName("上海");
    Long i = cityMapper.insertCity(createCityDto);

    List<CityDto> cityList = cityMapper.searchWithName("上海");
    Assert.assertEquals(cityList.get(0).getName(), "上海");
  }

  @Test
  public void testSearchWithName() {
    City city = insertCity();

    List<CityDto> cityList = cityMapper.searchWithName("上海");
    Assert.assertEquals(cityList.size(), 1);
    Assert.assertEquals(cityList.get(0).getId(), city.getId());
    Assert.assertEquals(cityList.get(0).getName(), city.getName());
  }

  @Test
  public void testSearchCondition() {
    City city = insertCity();

    SearchCityDto searchCityDto = new SearchCityDto();
    searchCityDto.setName("上海");

    List<CityDto> cityList = cityMapper.searchCondition(searchCityDto);
    Assert.assertEquals(cityList.size(), 1);
    Assert.assertEquals(cityList.get(0).getName(), city.getName());
  }

  private City insertCity() {
    City city = new City();
    city.setId(3L);
    city.setName("上海");
    int i = cityMapper.insert(city);

    return city;
  }
}
