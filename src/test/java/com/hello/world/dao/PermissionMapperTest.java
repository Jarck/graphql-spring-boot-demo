package com.hello.world.dao;

import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.result.PermissionDto;
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
public class PermissionMapperTest {
  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Autowired
  private PermissionMapper permissionMapper;

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
  public void TestInsertPermission() {
    CreatePermissionDto createPermissionDto = new CreatePermissionDto();
    createPermissionDto.setName("创建角色");
    createPermissionDto.setPermission("role:create");
    createPermissionDto.setResourceType("create");
    Long i = permissionMapper.insertPermission(createPermissionDto);

    List<PermissionDto> permissionList = permissionMapper.searchWithName("创建角色");
    Assert.assertEquals(permissionList.size(), 1);
    Assert.assertEquals(permissionList.get(0).getId(), createPermissionDto.getId());
    Assert.assertEquals(permissionList.get(0).getName(), createPermissionDto.getName());
    Assert.assertEquals(permissionList.get(0).getPermission(), createPermissionDto.getPermission());
    Assert.assertEquals(permissionList.get(0).getResourceType(), createPermissionDto.getResourceType());
  }

  @Test
  public void TestSearchWithName() {
    List<PermissionDto> permissionList = permissionMapper.searchWithName("读取用户");

    Assert.assertEquals(permissionList.size(), 1);
    Assert.assertEquals(permissionList.get(0).getId(), new Long(1));
    Assert.assertEquals(permissionList.get(0).getName(), "读取用户");
    Assert.assertEquals(permissionList.get(0).getPermission(), "user:read");
  }

  @Test
  public void TestFindByRoleId() {
    List<PermissionDto> admin_permissions = permissionMapper.searchWithRoleId(1L);
    Assert.assertEquals(admin_permissions.size(), 4);
    Assert.assertEquals(admin_permissions.get(0).getName(), "读取用户");
    Assert.assertEquals(admin_permissions.get(0).getPermission(), "user:read");

    List<PermissionDto> test_permissions = permissionMapper.searchWithRoleId(2L);
    Assert.assertEquals(test_permissions.size(), 1);
    Assert.assertEquals(test_permissions.get(0).getName(), "读取用户");
    Assert.assertEquals(admin_permissions.get(0).getPermission(), "user:read");
  }

  @Test
  public void TestFindByUserId() {
    List<PermissionDto> admin_permissions = permissionMapper.searchWithUserId(1L);
    Assert.assertEquals(admin_permissions.size(), 5);
    Assert.assertEquals(admin_permissions.get(0).getName(), "读取用户");
    Assert.assertEquals(admin_permissions.get(0).getPermission(), "user:read");

    List<PermissionDto> test_permissions = permissionMapper.searchWithUserId(2L);
    Assert.assertEquals(test_permissions.size(), 1);
    Assert.assertEquals(test_permissions.get(0).getName(), "读取用户");
    Assert.assertEquals(admin_permissions.get(0).getPermission(), "user:read");
  }
}
