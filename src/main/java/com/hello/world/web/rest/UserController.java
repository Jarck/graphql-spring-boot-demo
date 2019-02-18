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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author jarck-lou
 * @date 2018/12/28 15:33
 **/
@Slf4j
@RestController
@Api(value = "RESTFul用户", description = "RESTFul用户")
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
  @ApiOperation(value = "查询用户列表", notes = "根据查询条件获取用户信息")
  @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header")
  @GetMapping("")
  @RequiresPermissions("user:read")
  public ResponseBean<PageInfo<UserDto>> list(SearchUserDto searchUserDto, PageDto pageDto) {
    PageInfo<UserDto> userDtoPageInfo = userService.searchUserAndCityAndCompanyAndRoles(searchUserDto, pageDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, userDtoPageInfo);
  }

  /**
   * 获取用户详细信息
   *
   * @param id 用户ID
   * @return ResponseBean
   */
  @ApiOperation(value = "获取用户详细信息", notes = "根据用户ID获取用户详细信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
  })
  @GetMapping("{id}")
  @RequiresPermissions("user:read")
  public ResponseBean<UserDto> show(@PathVariable Long id) {
    UserDto user = userService.searchUserAndCityAndCompanyAndRolesWithId(id);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, user);
  }

  /**
   * 创建用户
   *
   * @param createUserDto 用户信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   * @throws ArgumentsException 参数异常
   */
  @ApiOperation(value = "创建用户")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "form"),
          @ApiImplicitParam(name = "phone", value = "手机号码", required = true, paramType = "form"),
          @ApiImplicitParam(name = "cityId", value = "城市ID", paramType = "form"),
          @ApiImplicitParam(name = "companyId", value = "公司ID", paramType = "form"),
          @ApiImplicitParam(name = "roleIds", value = "角色IDs", paramType = "form")
  })
  @PostMapping("")
  @RequiresPermissions("user:create")
  public ResponseBean<UserDto> create(@ApiIgnore @Validated CreateUserDto createUserDto, BindingResult bindingResult)
          throws ArgumentsException {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    UserDto userDto = userService.createUser(createUserDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, userDto);
  }

  /**
   * 更新用户
   *
   * @param editUserDto 用户信息
   * @return ResponseBean
   */
  @ApiOperation(value = "更新用户详细")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header")
  })
  @PutMapping("")
  @RequiresPermissions("user:edit")
  public ResponseBean<UserDto> update(@RequestBody EditUserDto editUserDto) {
    UserDto userDto = userService.updateUser(editUserDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, userDto);
  }
}
