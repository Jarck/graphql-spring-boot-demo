package com.hello.world.web.rest;

import com.hello.world.Application;
import com.hello.world.auth.JWTToken;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.service.IUserService;
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
public abstract class BaseMock extends AbstractShiroTest {
  @Mock
  protected StringRedisTemplate stringRedisTemplate;

  @Resource
  DataSource dataSource;

  @Autowired
  private WebApplicationContext webApplicationContext;
  protected MockMvc mockMvc;

  @Autowired
  protected SecurityManager securityManager;

  @Autowired
  private IUserService userService;

  protected String token = "";
  public Subject subject;

  @Before
  public void setup() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    setSecurityManager(securityManager);

    String phone = "18812345671";
    String verifyCode = "123456";

    this.mockMvc.perform(post("/api/login")
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
