package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
public interface IUserService {

  /**
   * find all users
   *
   * @return 用户列表
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
   * @param phone phone
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
   * @return 用户列表
   */
  List<User> searchWithCondition(SearchUserDto searchUserDto);

  /**
   * 分页查询
   *
   * @param searchUserDto 搜索条件
   * @param pageDto 分页信息
   * @return 用户page
   */
  PageInfo<User> searchWithCondition(SearchUserDto searchUserDto, PageDto pageDto);

  /**
   * 创建用户
   *
   * @param createUserDto 用户
   * @return 影响行数
   */
  Long createUser(CreateUserDto createUserDto);
}
