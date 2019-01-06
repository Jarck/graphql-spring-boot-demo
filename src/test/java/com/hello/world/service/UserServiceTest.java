package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.result.UserDto;
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
 * @date 2018/9/1 12:52
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class UserServiceTest {
  @Autowired
  private IUserService userService;

  @Test
  public void testGetUserByPhone() {
    UserDto userDto = userService.getUserByPhone("18812345671");
    Assert.assertEquals(userDto.getName(), "admin");
  }

  @Test
  public void testPageSearchWithCondition() {
    SearchUserDto searchUserDto = new SearchUserDto();
    searchUserDto.setName("admin");

    PageDto pageDto = new PageDto();
    PageInfo<UserDto> userPageInfo = userService.searchWithCondition(searchUserDto, pageDto);

    Assert.assertEquals(userPageInfo.getPageNum(), 1);
    Assert.assertEquals(userPageInfo.getPageSize(), 20);
    Assert.assertEquals(userPageInfo.getTotal(), 1L);
    Assert.assertEquals(userPageInfo.isHasNextPage(), false);
    Assert.assertEquals(userPageInfo.getList().get(0).getName(), "admin");
  }
}
