package com.hello.world.web.rest;

import com.hello.world.auth.JWTUtil;
import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.result.UserDto;
import com.hello.world.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jarck-lou
 * @date 2018/12/28 10:00
 **/
public class BaseController {
  @Autowired
  private IUserService userService;

  /**
   * 请求参数校验
   *
   * @param bindingResult 校验结果
   * @return ResponseBean
   */
  protected ResponseBean validateError(BindingResult bindingResult) {
    List<Map<String, String>> errors = bindingResult.getFieldErrors().stream().map(error -> {
      Map<String, String> map = new HashMap<>();
      map.put(error.getField(), error.getDefaultMessage());

      return map;
    }).collect(Collectors.toList());

    return new ResponseBean<>(CommonStatus.BAD_REQUEST, ResponseMessage.ARGUMENTS_ERROR, errors);
  }

  /**
   * 获取当前登录用户
   *
   * @return userDto
   */
  protected UserDto currentUser() {
    String token = SecurityUtils.getSubject().getPrincipal().toString();

    return userService.searchWithPhone(JWTUtil.getPhone(token));
  }
}
