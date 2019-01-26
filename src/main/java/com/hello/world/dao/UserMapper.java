package com.hello.world.dao;

import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

/**
 * 用户DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
public interface UserMapper {
  /**
   * 按ID删除
   *
   * @param id ID
   * @return 影响行数
   */
  int deleteByPrimaryKey(Long id);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insert(User record);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insertSelective(User record);

  /**
   * 按ID查询
   *
   * @param id ID
   * @return 用户
   */
  UserDto selectByPrimaryKey(Long id);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(User record);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKey(User record);

  /**
   * 所有用户
   *
   * @return 用户列表
   */
  List<UserDto> findAll();

  /**
   * 按手机号查询用户
   *
   * @param phone phone
   * @return 用户
   */
  @Cacheable("users")
  UserDto selectByPhone(String phone);

  /**
   * 创建用户
   *
   * @param createUserDto createUserDto
   * @return 影响的行数
   */
  Long insertUser(CreateUserDto createUserDto);

  /**
   * 按条件查询
   *
   * @param searchUserDto searchUserDto
   * @return 用户列表
   */
  List<UserDto> searchCondition(SearchUserDto searchUserDto);

  /**
   * 按条件查询
   *
   * @param searchUserDto searchUserDto
   * @return 用户列表
   */
  List<UserDto> searchUserAndCityAndCompanyAndRoles(SearchUserDto searchUserDto);

  /**
   * 按ID查询
   *
   * @param id id
   * @return 用户
   */
  UserDto searchUserAndCityAndCompanyAndRolesWithId(@Param(value = "id") Long id);

  /**
   * 按手机号查询
   *
   * @param phone phone
   * @return 用户
   */
  UserDto searchUserAndCityAndCompanyAndRolesWithPhone(@Param(value = "phone") String phone);
}
