package hello.service;

import hello.dto.condition.CreateUserDto;
import hello.dto.condition.LoginDto;
import hello.dto.condition.SearchUserDto;
import hello.dto.result.UserDto;
import hello.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserService {

    /**
     * find all users
     *
     * @return
     */
    List<User> findAll();

    /**
     * find by id
     *
     * @param userId user id
     * @return userDto
     */
    UserDto searchWithId(Long userId);

    /**
     * get by phone
     *
     * @param phone phone
     * @return userDto
     */
    UserDto searchWithPhone(@Param("phone") String phone);

    /**
     * find user by phone
     *
     * @param phone
     * @return user
     */
    User getUserByPhone(@Param("phone") String phone);

    /**
     * user login
     *
     * @param loginDto login params
     * @return token
     */
    String login(LoginDto loginDto);

    /**
     * 按条件查询
     *
     * @param searchUserDto 搜索条件
     * @return
     */
    List<User> searchWithCondition(SearchUserDto searchUserDto);

    /**
     * 创建用户
     *
     * @param createUserDto
     * @return
     */
    String createUser(CreateUserDto createUserDto);
}
