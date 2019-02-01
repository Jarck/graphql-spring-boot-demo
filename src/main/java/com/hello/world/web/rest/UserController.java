package com.hello.world.web.rest;

import com.github.pagehelper.PageInfo;
import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.create.CreateUserDto;
import com.hello.world.dto.edit.EditUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.exception.ArgumentsException;
import com.hello.world.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jarck-lou
 * @date 2018/12/28 15:33
 **/
@Slf4j
@RestController
@Api(value = "Rest用户", description = "Rest用户")
@RequestMapping("api/users")
public class UserController extends BaseController {
  @Autowired
  private IUserService userService;

  /**
   * 查询用户列表
   *
   * @param searchUserDto 查询条件
   * @param pageDto 分页信息
   * @return ResponseBean
   */
  @ApiOperation("")
  @GetMapping("")
  @RequiresPermissions("user:read")
  public ResponseBean list(SearchUserDto searchUserDto, PageDto pageDto) {
    PageInfo<UserDto> userDtoPageInfo = userService.searchWithCondition(searchUserDto, pageDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, userDtoPageInfo);
  }

  /**
   * 获取用户详细信息
   *
   * @param id 用户ID
   * @return ResponseBean
   */
  @GetMapping("{id}")
  @RequiresPermissions("user:read")
  public ResponseBean show(@PathVariable Long id) {
    UserDto user = userService.searchWithId(id);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, user);
  }

  /**
   * 创建用户
   *
   * @param userDto 用户信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   * @throws ArgumentsException 参数异常
   */
  @PostMapping("")
  @RequiresPermissions("user:create")
  public ResponseBean create(@Validated CreateUserDto userDto, BindingResult bindingResult) throws ArgumentsException {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    UserDto user = userService.createUser(userDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, user);
  }

  /**
   * 更新用户
   *
   * @param editUserDto 用户信息
   * @return ResponseBean
   */
  @PutMapping("")
  @RequiresPermissions("user:edit")
  public ResponseBean update(@RequestBody EditUserDto editUserDto) {
    UserDto user = userService.updateUser(editUserDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, user);
  }
}
