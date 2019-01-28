package com.hello.world.dao;

import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.result.RoleDto;
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
public class RoleMapperTest {
  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();
  @Autowired
  private RoleMapper roleMapper;
  @Resource
  private DataSource dataSource;

  @Before
  public void setUp() throws Exception {
    Operation operation = Operations.sequenceOf(
            Operations.deleteAllFrom("user", "role", "user_role", "permission", "role_permission"),
            Operations.insertInto("user")
                    .columns("id", "name", "phone", "status")
                    .values(1, "admin", "18812345671", 1)
                    .values(2, "test", "18812345672", 1)
                    .build(),
            Operations.insertInto("role")
                    .columns("id", "name", "remark")
                    .values(1, "admin", "系统管理员")
                    .values(2, "test", "测试")
                    .build(),
            Operations.insertInto("user_role")
                    .columns("id", "user_id", "role_id")
                    .values(1, 1, 1)
                    .values(2, 1, 2)
                    .values(3, 2, 2)
                    .build(),
            Operations.insertInto("permission")
                    .columns("id", "name", "permission", "resource_type")
                    .values(1, "读取用户", "user:read", "read")
                    .values(2, "创建用户", "user:create", "create")
                    .values(3, "编辑用户", "user:edit", "edit")
                    .values(4, "删除用户", "user:delete", "delete")
                    .build(),
            Operations.insertInto("role_permission")
                    .columns("id", "role_id", "permission_id")
                    .values(1, 1, 1)
                    .values(2, 1, 2)
                    .values(3, 1, 3)
                    .values(4, 1, 4)
                    .values(5, 2, 1)
                    .build()
    );

    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetupTracker.launchIfNecessary(dbSetup);
  }

  @Test
  public void testInsertRole() {
    CreateRoleDto createRoleDto = new CreateRoleDto();
    createRoleDto.setName("test2");
    createRoleDto.setRemark("测试2");
    Long i = roleMapper.insertRole(createRoleDto);

    List<RoleDto> roleList = roleMapper.searchWithName("test2");
    Assert.assertEquals(roleList.size(), 1);
    Assert.assertEquals(roleList.get(0).getId(), createRoleDto.getId());
    Assert.assertEquals(roleList.get(0).getName(), createRoleDto.getName());
    Assert.assertEquals(roleList.get(0).getRemark(), createRoleDto.getRemark());
  }

  @Test
  public void testSearchWithName() {
    List<RoleDto> roleList = roleMapper.searchWithName("admin");

    Assert.assertEquals(roleList.size(), 1);
    Assert.assertEquals(roleList.get(0).getId(), new Long(1));
    Assert.assertEquals(roleList.get(0).getName(), "admin");
    Assert.assertEquals(roleList.get(0).getRemark(), "系统管理员");
  }

  @Test
  public void testFindAll() {
    List<RoleDto> roles = roleMapper.findAll();
    Assert.assertEquals(roles.size(), 2);
    Assert.assertEquals(roles.get(0).getName(), "admin");
  }

  @Test
  public void testFindByUserId() {
    List<RoleDto> admin_roles = roleMapper.searchWithUserId(1L);
    Assert.assertEquals(admin_roles.size(), 2);
    Assert.assertEquals(admin_roles.get(0).getName(), "admin");

    List<RoleDto> test_roles = roleMapper.searchWithUserId(2L);
    Assert.assertEquals(test_roles.size(), 1);
    Assert.assertEquals(test_roles.get(0).getName(), "test");
  }

  @Test
  public void testSearchRoleAndPermissions() {
    RoleDto roleDto = roleMapper.searchRoleAndPermissions(1L);

    Assert.assertEquals(roleDto.getName(), "admin");
    Assert.assertEquals(roleDto.getPermissions().size(), 4);
    Assert.assertEquals(roleDto.getPermissions().get(0).getName(), "读取用户");
  }
}
