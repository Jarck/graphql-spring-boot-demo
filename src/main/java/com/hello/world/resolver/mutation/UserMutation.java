package com.hello.world.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.exception.GraphQLValidateException;
import com.hello.world.service.IUserService;
import com.hello.world.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
   * @throws GraphQLValidateException 参数校验异常
   */
  public UserDto createUser(CreateUserDto createUserDto) throws GraphQLValidateException {

    // 校验参数
    Map<String, StringBuffer> errorMap = ValidatorUtil.validate(createUserDto);
    if (errorMap != null) {
      throw new GraphQLValidateException(errorMap.toString());
    }

    userService.createUser(createUserDto);
    UserDto userDto = userService.searchWithId(createUserDto.getId());

    return userDto;
  }
}
