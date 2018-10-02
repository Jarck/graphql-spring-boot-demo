package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.entity.User;
import com.hello.world.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class UserMutation implements GraphQLMutationResolver {
  @Autowired
  private IUserService userService;

  /**
   * 创建用户
   *
   * @param createUserDto createUserDto
   * @return 用户
   */
  public User createUser(CreateUserDto createUserDto) {
    Long userId = userService.createUser(createUserDto);
    User user = userService.searchWithId(userId);

    return user;
  }
}
