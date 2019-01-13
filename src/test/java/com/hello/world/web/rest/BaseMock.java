package com.hello.world.web.rest;

import com.hello.world.Application;
import com.hello.world.auth.JWTToken;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.service.IUserService;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author jarck-lou
 * @date 2019/01/09 14:24
 **/
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseMock extends AbstractShiroTest {
  @Mock
  protected StringRedisTemplate stringRedisTemplate;

  @Resource
  DataSource dataSource;

  @Autowired
  private WebApplicationContext webApplicationContext;
  protected MockMvc mockMvc;

  @Autowired
  protected SecurityManager securityManager;

  private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

  @Autowired
  private IUserService userService;

  protected String token = "";
  public Subject subject;

  @Before
  public void setup() throws Exception {
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

    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    setSecurityManager(securityManager);

    String phone = "18812345671";
    String verifyCode = "123456";

    MvcResult mvcResult = this.mockMvc.perform(post("/api/login")
            .param("phone", phone)
            .param("verifyCode", verifyCode))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

    LoginDto loginUser = new LoginDto();
    loginUser.setPhone(phone);
    loginUser.setVerifyCode(verifyCode);
    token = userService.login(loginUser);

    ValueOperations valueOperations = mock(ValueOperations.class);
    when(stringRedisTemplate.opsForValue()).thenReturn(valueOperations);
    when(valueOperations.get(phone + "_token")).thenReturn(token);

    JWTToken jwtToken = new JWTToken(token);
    subject = getSubject();
    subject.login(jwtToken);
    setSubject(subject);
  }
}
