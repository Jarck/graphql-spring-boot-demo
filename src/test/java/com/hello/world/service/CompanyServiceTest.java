package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.result.CompanyDto;
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
 * @date 2018/11/05 14:27
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CompanyServiceTest {
  @Autowired
  private ICompanyService companyService;

  @Test
  public void testPageSearchCondition() {
    SearchCompanyDto searchCompanyDto = new SearchCompanyDto();
    searchCompanyDto.setName("杭州xxx有限公司");

    PageDto pageDto = new PageDto();

    PageInfo<CompanyDto> companyPageInfo = companyService.searchCondition(searchCompanyDto, pageDto);

    Assert.assertEquals(companyPageInfo.getPageNum(), 1);
    Assert.assertEquals(companyPageInfo.getPageSize(), 20);
    Assert.assertEquals(companyPageInfo.getTotal(), 1);
    Assert.assertEquals(companyPageInfo.isHasNextPage(), false);
    Assert.assertEquals(companyPageInfo.getList().get(0).getShortName(), "杭州xxx");
  }
}
