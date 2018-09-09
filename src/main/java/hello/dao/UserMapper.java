package hello.dao;

import hello.dto.condition.SearchUserDto;
import hello.entity.User;
import org.apache.ibatis.annotations.Mapper;
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
  int deleteByPrimaryKey(Long id);

  int insert(User record);

  int insertSelective(User record);

  User selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);

  List<User> findAll();

  @Cacheable("users")
  User selectByPhone(String phone);

  /**
   * 创建用户
   *
   * @param record 用户对象
   * @return 影响的行数
   */
  Integer insertUser(User record);

  /**
   * 按条件查询
   *
   * @param searchUserDto
   * @return
   */
  List<User> searchCondition(SearchUserDto searchUserDto);
}