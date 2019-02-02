package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.result.RoleDto;
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
 * @date 2018/11/05 15:19
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RoleServiceTest {
  @Autowired
  private IRoleService roleService;

  @Test
  public void testPageFindAll() {
    PageDto pageDto = new PageDto();
    pageDto.setDesc(false);

    PageInfo<RoleDto> rolePageInfo = roleService.findAll(pageDto);

    Assert.assertEquals(rolePageInfo.getPageNum(), 1);
    Assert.assertEquals(rolePageInfo.getPageSize(), 20);
    Assert.assertEquals(rolePageInfo.getTotal(), 2L);
    Assert.assertEquals(rolePageInfo.isHasNextPage(), false);
    Assert.assertEquals(rolePageInfo.getList().get(0).getName(), "admin");
  }
}
