package hello.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import hello.entity.Role;
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

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@MybatisTest
@Transactional
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Resource
    private DataSource dataSource;

    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();
    @Before
    public void setUp() throws Exception {
        Operation operation = Operations.sequenceOf(
                Operations.deleteAllFrom("user", "role", "user_role"),
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
                    .build()
        );

        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public void testFindAll() {
        List<Role> roles = roleMapper.findAll();
        Assert.assertEquals(roles.size(), 2);
        Assert.assertEquals(roles.get(0).getName(), "admin");
    }

    @Test
    public void testFindByUserId() {
        List<Role> admin_roles = roleMapper.findByUserId(1L);
        Assert.assertEquals(admin_roles.size(), 2);
        Assert.assertEquals(admin_roles.get(0).getName(), "admin");

        List<Role> test_roles = roleMapper.findByUserId(2L);
        Assert.assertEquals(test_roles.size(), 1);
        Assert.assertEquals(test_roles.get(0).getName(), "test");
    }
}
