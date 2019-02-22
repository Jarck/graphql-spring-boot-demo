package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.edit.EditRoleDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.dto.result.RolePermissionsDto;
import com.hello.world.service.IRolePermissionsService;
import com.hello.world.service.IRoleService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2019/02/01 14:36
 **/
@Slf4j
@RestController
@Api(value = "RESTFul角色", description = "RESTFul角色")
@RequestMapping("api/roles")
public class RoleController extends BaseController {
  @Autowired
  private IRoleService roleService;

  @Autowired
  private IRolePermissionsService rolePermissionsService;

  /**
   * 获取角色列表
   *
   * @return ResponseBean
   */
  @ApiOperation(value = "获取角色列表", notes = "获取全部角色信息")
  @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header")
  @GetMapping("")
  @RequiresPermissions("role:read")
  public ResponseBean<List<RoleDto>> list() {
    List<RoleDto> roleList = roleService.findAll();

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, roleList);
  }

  /**
   * 获取角色详细信息
   *
   * @param id 角色ID
   * @return ResponseBean
   */
  @ApiOperation(value = "获取角色详细信息", notes = "根据角色ID获取角色详细信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long", paramType = "path")
  })
  @GetMapping("{id}")
  @RequiresPermissions("role:read")
  public ResponseBean<RoleDto> show(@PathVariable Long id) {
    RoleDto roleDto = roleService.searchWithId(id);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 获取角色对应的权限信息
   *
   * @param id 角色ID
   * @return ResponseBean
   */
  @ApiOperation(value = "获取角色对应的权限信息", notes = "根据角色ID获取角色对应的权限信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long", paramType = "path")
  })
  @GetMapping("{id}/permissions")
  @RequiresPermissions("role:read")
  public ResponseBean<RolePermissionsDto> getRolePermissions(@PathVariable Long id) {
    RolePermissionsDto rolePermissionsDto = roleService.searchRoleAndPermissions(id);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, rolePermissionsDto);
  }

  /**
   * 创建角色
   *
   * @param createRoleDto 角色信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   */
  @ApiOperation(value = "创建角色")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "name", value = "角色名称", defaultValue = "admin",
                  required = true, paramType = "form"),
          @ApiImplicitParam(name = "remark", value = "备注信息", paramType = "form")
  })
  @PostMapping("")
  @RequiresPermissions("role:create")
  @SuppressWarnings("unchecked")
  public ResponseBean<RoleDto> create(@ApiIgnore @Validated CreateRoleDto createRoleDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    RoleDto roleDto = roleService.createRole(createRoleDto);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 更新角色
   *
   * @param editRoleDto 角色信息
   * @return ResponseBean
   */
  @ApiOperation(value = "更新角色信息")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header"),
          @ApiImplicitParam(name = "id", value = "角色ID", required = true, paramType = "form"),
          @ApiImplicitParam(name = "name", value = "角色名称", required = true, paramType = "form"),
          @ApiImplicitParam(name = "status", value = "状态", paramType = "form"),
          @ApiImplicitParam(name = "remark", value = "备注", paramType = "form")
  })
  @PutMapping("")
  @RequiresPermissions("role:edit")
  public ResponseBean<RoleDto> update(@ApiIgnore @RequestBody EditRoleDto editRoleDto) {
    RoleDto roleDto = roleService.updateRole(editRoleDto);

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 设置角色对应的权限
   *
   * @param editRoleDto 角色信息
   * @return ResponseBean
   * @throws NotFoundException notFoundException
   */
  @ApiOperation(value = "设置角色对应的权限")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "auth-token", value = "token(required)", paramType = "header")
  })
  @PutMapping("updateRolePermissions")
  @RequiresPermissions("role:edit")
  public ResponseBean updateRolePermissions(@RequestBody EditRoleDto editRoleDto)
          throws NotFoundException {
    rolePermissionsService.updateRolePermissions(editRoleDto.getId(), editRoleDto.getPermissionIds());

    return new ResponseBean<>(CommonStatus.OK, ResponseMessage.SUCCESS, null);
  }
}
