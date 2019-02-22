package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.edit.EditPermissionDto;
import com.hello.world.dto.result.PermissionDto;
import com.hello.world.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2019/02/02 11:21
 **/
@Slf4j
@RestController
@Api(value = "RESTFul权限", description = "RESTFul权限")
@RequestMapping("api/permissions")
public class PermissionController extends BaseController {
  @Autowired
  private IPermissionService permissionService;

  /**
   * 获取权限列表
   *
   * @return ResponseBean
   */
  @ApiOperation(value = "获取权限列表", notes = "获取全部权限信息")
  @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header")
  @GetMapping("")
  @RequiresPermissions("permission:read")
  public ResponseBean<List<PermissionDto>> list() {
    List<PermissionDto> permissionList = permissionService.findAll();

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, permissionList);
  }

  /**
   * 创建权限
   *
   * @param createPermissionDto 权限信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   */
  @ApiOperation(value = "创建权限")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "name", value = "权限名称", defaultValue = "读取用户",
                  required = true, paramType = "form"),
          @ApiImplicitParam(name = "permission", value = "权限", defaultValue = "user:read",
                  required = true, paramType = "form"),
          @ApiImplicitParam(name = "resourceType", value = "权限类型", defaultValue = "read",
                  required = true, paramType = "form")
  })
  @PostMapping("")
  @RequiresPermissions("permission:create")
  @SuppressWarnings("unchecked")
  public ResponseBean<PermissionDto> create(@ApiIgnore @RequestBody @Validated CreatePermissionDto createPermissionDto,
                             BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    PermissionDto permissionDto = permissionService.createPermission(createPermissionDto);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, permissionDto);
  }

  /**
   * 更新权限
   *
   * @param editPermissionDto 权限信息
   * @return ResponseBean
   * @throws NotFoundException notFoundException
   */
  @ApiOperation(value = "更新权限信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header")
  })
  @PutMapping("")
  @RequiresPermissions("permission:edit")
  public ResponseBean<PermissionDto> update(@RequestBody EditPermissionDto editPermissionDto)
          throws NotFoundException {
    PermissionDto permissionDto = permissionService.updatePermission(editPermissionDto);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, permissionDto);
  }
}
