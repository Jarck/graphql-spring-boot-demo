package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.SystemConstant;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.entity.User;
import com.hello.world.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
@RestController
@Api(value = "Rest登录", description = "Rest登录")
public class LoginController extends BaseController {
  @Autowired
  private IUserService userService;

  /**
   * 用户登录
   *
   * @param loginUser loginUser
   * @param bindingResult bindingResult
   * @return 登录结果
   */
  @ApiOperation("api/login")
  @PostMapping("api/login")
  public ResponseBean login(@Validated LoginDto loginUser, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    String token = userService.login(loginUser);
    User user = userService.getUserByPhone(loginUser.getPhone());
    Map<String, Object> result = new HashMap<>(1);
    result.put("user", user);
    result.put(SystemConstant.TOKEN_HEADER, token);

    return new ResponseBean(CommonStatus.OK, "success", result);
  }

  /**
   * 401 request
   *
   * @return 401
   */
  @RequestMapping("401")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseBean unauthorized() {
    return new ResponseBean(HttpStatus.UNAUTHORIZED.value(), "Unauthorized", "token失效或没有访问权限");
  }
}
