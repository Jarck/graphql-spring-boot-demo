package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchCityDto;
import com.hello.world.dto.result.CityDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jarck-lou
 * @date 2018/11/05 14:56
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CityServiceTest {
  @Autowired
  private ICityService cityService;

  @Test
  public void testPageSearchWithCondition() {
    SearchCityDto searchCityDto = new SearchCityDto();
    searchCityDto.setName("杭州");

    PageDto pageDto = new PageDto();

    PageInfo<CityDto> cityPageInfo = cityService.searchWithCondition(searchCityDto, pageDto);

    Assert.assertEquals(cityPageInfo.getPageNum(), 1);
    Assert.assertEquals(cityPageInfo.getPageSize(), 20);
    Assert.assertEquals(cityPageInfo.getTotal(), 1L);
    Assert.assertEquals(cityPageInfo.isHasNextPage(), false);
    Assert.assertEquals(cityPageInfo.getList().get(0).getName(), "杭州");
  }
}
