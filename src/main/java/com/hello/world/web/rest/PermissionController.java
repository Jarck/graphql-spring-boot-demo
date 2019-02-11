package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.edit.EditPermissionDto;
import com.hello.world.dto.result.PermissionDto;
import com.hello.world.exception.ArgumentsException;
import com.hello.world.service.IPermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2019/02/02 11:21
 **/
@Slf4j
@RestController
@Api(value = "Rest权限", description = "Rest权限")
@RequestMapping("api/permissions")
public class PermissionController extends BaseController {
  @Autowired
  private IPermissionService permissionService;

  /**
   * 获取权限列表
   *
   * @return ResponseBean
   */
  @GetMapping("")
  @RequiresPermissions("permission:read")
  public ResponseBean list() {
    List<PermissionDto> permissionList = permissionService.findAll();

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, permissionList);
  }

  /**
   * 创建权限
   *
   * @param createPermissionDto 权限信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   * @throws ArgumentsException 参数异常
   */
  @PostMapping("")
  @RequiresPermissions("permission:create")
  public ResponseBean create(@RequestBody @Validated CreatePermissionDto createPermissionDto,
                             BindingResult bindingResult)
          throws ArgumentsException {

    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    PermissionDto permissionDto = permissionService.createPermission(createPermissionDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, permissionDto);
  }

  /**
   * 更新权限
   *
   * @param editPermissionDto 权限信息
   * @return ResponseBean
   * @throws NotFoundException notFoundException
   */
  @PutMapping("")
  @RequiresPermissions("permission:edit")
  public ResponseBean update(@RequestBody EditPermissionDto editPermissionDto)
          throws NotFoundException {
    PermissionDto permissionDto = permissionService.updatePermission(editPermissionDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, permissionDto);
  }
}
