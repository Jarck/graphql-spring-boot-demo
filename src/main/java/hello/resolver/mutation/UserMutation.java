package hello.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import hello.dto.create.CreateUserDto;
import hello.entity.User;
import hello.service.IUserService;
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
   * @param input
   * @return
   */
  public User createUser(CreateUserDto input) {
    Long user_id = userService.createUser(input);
    User user = userService.searchWithId(user_id);

    return user;
  }
}
