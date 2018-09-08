package hello.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import hello.dto.condition.CreateUserDto;
import hello.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public String createUser(CreateUserDto input) {
        return userService.createUser(input);
    }
}
