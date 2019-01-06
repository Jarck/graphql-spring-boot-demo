package com.hello.world.dao;

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
  @Resource
  private DataSource dataSource;

  @Before
  public void setup() {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("user"),
            Operations.insertInto("user")
                    .columns("id", "name", "phone", "status")
                    .values(1, "test", "18812345671", 1)
                    .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  public void testFindAll() {
    List<UserDto> users = userMapper.findAll();
    Assert.assertEquals(users.size(), 1);
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
}
