package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.constant.SystemConstant;
import com.hello.world.dto.condition.LoginDto;
import com.hello.world.entity.User;
import com.hello.world.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Slf4j
@RestController
@Api(value = "RESTFul登录", description = "RESTFul登录")
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
  @ApiOperation(value = "api/login", notes = "用户登录")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "phone", value = "手机号码", defaultValue = "18812345671",
                  required = true, paramType = "form"),
          @ApiImplicitParam(name = "verifyCode", value = "验证码", defaultValue = "123456",
                  required = true, paramType = "form")
  })
  @PostMapping("api/login")
  public ResponseBean login(@ApiIgnore @Validated LoginDto loginUser, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    String token = userService.login(loginUser);
    User user = userService.getUserByPhone(loginUser.getPhone());
    Map<String, Object> result = new HashMap<>(1);
    result.put("user", user);
    result.put(SystemConstant.TOKEN_HEADER, token);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, result);
  }

  /**
   * 401 request
   *
   * @return 401
   */
  @ApiOperation(value = "401")
  @RequestMapping("401")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseBean unauthorized() {
    return new ResponseBean<>(HttpStatus.UNAUTHORIZED.value(), ResponseMessage.UNAUTHORIZED, "token失效或没有访问权限");
  }
}
