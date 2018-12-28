package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
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

  /**
   * 请求参数校验
   *
   * @param bindingResult 校验信息
   * @return ResponseBean
   */
  protected ResponseBean validateError(BindingResult bindingResult) {
    List<Map<String, String>> errors = bindingResult.getFieldErrors().stream().map(error -> {
      Map<String, String> map = new HashMap<>();
      map.put(error.getField(), error.getDefaultMessage());

      return map;
    }).collect(Collectors.toList());

    return new ResponseBean(CommonStatus.BAD_REQUEST, ResponseMessage.ARGUMENTS_ERROR, errors);
  }
}
