package hello.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import hello.dto.condition.SearchUserDto;
import hello.entity.User;
import hello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户Query
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Component
public class UserQuery implements GraphQLQueryResolver {
  @Autowired
  private IUserService userService;

  /**
   * 通过查询条件查询用户
   *
   * @param input
   * @return
   */
  public List<User> searchUsers(SearchUserDto input) {
    return userService.searchWithCondition(input);
  }

  /**
   * 通过ID查询用户
   *
   * @param userId
   * @return
   */
  public User getUserWithId(Long userId) {
    return userService.searchWithId(userId);
  }

  /**
   * 通过手机号查询用户
   *
   * @param phone
   * @return
   */
  public User getUserWithPhone(String phone) {
    return userService.getUserByPhone(phone);
  }
}
