package com.hello.world.web.rest;

import com.github.pagehelper.PageInfo;
import com.hello.world.constant.CommonStatus;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.condition.SearchUserDto;
import com.hello.world.dto.result.UserDto;
import com.hello.world.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jarck-lou
 * @date 2018/12/28 15:33
 **/
@Slf4j
@RestController
@Api(value = "Rest用户", description = "Rest用户")
@RequestMapping("api")
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
  @ApiOperation("users")
  @GetMapping("users")
  public ResponseBean list(SearchUserDto searchUserDto, PageDto pageDto) {
    UserDto userDto = currentUser();

    PageInfo<UserDto> userDtoPageInfo = userService.searchWithCondition(searchUserDto, pageDto);

    return new ResponseBean(CommonStatus.OK, "success", userDtoPageInfo);
  }
}
