package com.hello.world.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.entity.User;
import com.hello.world.exception.GraphQLNotFoundException;
import com.hello.world.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class UserQuery implements GraphQLQueryResolver {
  @Autowired
  private IUserService userService;

  /**
   * 通过ID查询用户
   *
   * @param userId 用户ID
   * @return 用户
   */
  public User searchUserWithId(Long userId) {
    UserDto userDto = userService.searchWithId(userId);

    if (userDto == null) {
      throw new GraphQLNotFoundException("Not found user with id " + userId);
    }
    return userDto;
//    return userService.searchWithId(userId);
  }

  /**
   * 通过手机号查询用户
   *
   * @param phone 手机号
   * @return 用户
   */
  public User searchUserWithPhone(String phone) {
    return userService.getUserByPhone(phone);
  }

  /**
   * 通过查询条件查询用户
   *
   * @param searchUserDto searchUserDto
   * @return 用户列表
   */
  public List<User> searchUsers(SearchUserDto searchUserDto) {
    return userService.searchWithCondition(searchUserDto);
  }
}
