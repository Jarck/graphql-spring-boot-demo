package com.hello.world.dao;

import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.entity.User;
import com.hello.world.enums.UserStatusEnum;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@MybatisTest
@Transactional
public class UserMapperTest {
  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();
  @Autowired
  private UserMapper userMapper;

  @Autowired
  private CityMapper cityMapper;

  @Resource
  private DataSource dataSource;

  @Before
  public void setup() {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("user", "city", "company", "role", "user_role"),
            Operations.insertInto("user")
                    .columns("id", "name", "phone", "status", "city_id", "company_id")
                    .values(1, "test", "18812345671", 1, 1, 1)
                    .values(2, "user", "18812345672", 1, 1, 1)
                    .values(3, "admin", "18812345673", 1, 2, 2)
                    .build(),
            Operations.insertInto("city")
                    .columns("id", "name")
                    .values(1, "杭州")
                    .values(2, "苏州")
                    .build(),
            Operations.insertInto("company")
                    .columns("id", "name", "short_name", "city_id")
                    .values(1, "杭州xxx有限公司", "杭州xxx", 1)
                    .values(2, "苏州xxx有限公司", "苏州xxx", 2)
                    .build(),
            Operations.insertInto("role")
                    .columns("id", "name", "remark")
                    .values(1, "admin", "系统管理员")
                    .values(2, "test", "测试")
                    .build(),
            Operations.insertInto("user_role")
                    .columns("id", "user_id", "role_id")
                    .values(1, 1, 1)
                    .values(2, 2, 1)
                    .values(3, 2, 2)
                    .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  public void testFindAll() {
    List<UserDto> users = userMapper.findAll();
    Assert.assertEquals(users.size(), 3);
    Assert.assertEquals(users.get(0).getName(), "test");
  }

  @Test
  public void testSelectByPhone() {
    UserDto userDto = userMapper.selectByPhone("18812345671");
    Assert.assertEquals(userDto.getName(), "test");
  }

  @Test
  public void testInsertUser() {
    CreateUserDto createUserDto = new CreateUserDto();
    createUserDto.setName("test2");
    createUserDto.setPhone("12345678902");
    Long i = userMapper.insertUser(createUserDto);

    UserDto user = userMapper.selectByPhone("12345678902");
    Assert.assertEquals(user.getId(), createUserDto.getId());
    Assert.assertEquals(user.getName(), createUserDto.getName());
    Assert.assertEquals(user.getPhone(), createUserDto.getPhone());
  }

  @Test
  public void testInsert() {
    User user = new User();
    user.setName("test_enum");
    user.setPhone("12345678901");
    user.setStatus(UserStatusEnum.ARCHIVED);
    int i = userMapper.insert(user);

    UserDto user_insert = userMapper.selectByPhone("12345678901");
    Assert.assertEquals(user.getPhone(), user_insert.getPhone());
    Assert.assertEquals(user_insert.getStatus(), UserStatusEnum.ARCHIVED);
  }

  @Test
  public void testSearchCondition() {
    SearchUserDto searchUserDto = new SearchUserDto();
    searchUserDto.setName("test");

    List<UserDto> users = userMapper.searchCondition(searchUserDto);
    Assert.assertEquals(users.size(), 1);
    Assert.assertEquals(users.get(0).getName(), "test");
  }

  @Test
  public void testSearchUserAndCityAndCompanyAndRoles() {
    SearchUserDto searchUserDto = new SearchUserDto();
    searchUserDto.setName("user");

    List<UserDto> users = userMapper.searchUserAndCityAndCompanyAndRoles(searchUserDto);
    Assert.assertEquals(users.size(), 1);
    Assert.assertEquals(users.get(0).getName(), "user");
    Assert.assertEquals(users.get(0).getCityDto().getName(), "杭州");
    Assert.assertEquals(users.get(0).getCompanyDto().getName(), "杭州xxx有限公司");
    Assert.assertEquals(users.get(0).getRoles().size(), 2);
    Assert.assertEquals(users.get(0).getRoles().get(0).getName(), "admin");
  }
}
