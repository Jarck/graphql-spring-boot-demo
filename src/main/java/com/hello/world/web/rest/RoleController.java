package com.hello.world.web.rest;

import com.hello.world.constant.CommonStatus;
import com.hello.world.constant.ResponseMessage;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.edit.EditRoleDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.exception.ArgumentsException;
import com.hello.world.service.IRolePermissionsService;
import com.hello.world.service.IRoleService;
import io.swagger.annotations.Api;
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

import java.util.List;

/**
 * @author jarck-lou
 * @date 2019/02/01 14:36
 **/
@Slf4j
@RestController
@Api(value = "Rest角色", description = "Rest角色")
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
  @GetMapping("")
  @RequiresPermissions("role:read")
  public ResponseBean list() {
    List<RoleDto> roleList = roleService.findAll();

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, roleList);
  }

  /**
   * 获取角色详细信息
   *
   * @param id 角色ID
   * @return ResponseBean
   */
  @GetMapping("{id}")
  @RequiresPermissions("role:read")
  public ResponseBean show(@PathVariable Long id) {
    RoleDto roleDto = roleService.searchWithId(id);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 获取角色对应的权限信息
   *
   * @param id 角色ID
   * @return ResponseBean
   */
  @GetMapping("{id}/permissions")
  @RequiresPermissions("role:read")
  public ResponseBean getRolePermissions(@PathVariable Long id) {
    RoleDto roleDto = roleService.searchRoleAndPermissions(id);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 创建角色
   *
   * @param createRoleDto 角色信息
   * @param bindingResult 校验对象
   * @return ResponseBean
   * @throws ArgumentsException 参数异常
   */
  @PostMapping("")
  @RequiresPermissions("role:create")
  public ResponseBean create(@Validated CreateRoleDto createRoleDto, BindingResult bindingResult)
          throws ArgumentsException {
    if (bindingResult.hasErrors()) {
      return validateError(bindingResult);
    }

    RoleDto roleDto = roleService.createRole(createRoleDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 更新角色
   *
   * @param editRoleDto 角色信息
   * @return ResponseBean
   */
  @PutMapping("")
  @RequiresPermissions("role:edit")
  public ResponseBean update(@RequestBody EditRoleDto editRoleDto) {
    RoleDto roleDto = roleService.updateRole(editRoleDto);

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, roleDto);
  }

  /**
   * 设置角色对应的权限
   *
   * @param editRoleDto 角色信息
   * @return ResponseBean
   * @throws NotFoundException notFoundException
   */
  @PutMapping("updateRolePermissions")
  @RequiresPermissions("role:edit")
  public ResponseBean updateRolePermissions(@RequestBody EditRoleDto editRoleDto)
          throws NotFoundException {
    rolePermissionsService.updateRolePermissions(editRoleDto.getId(), editRoleDto.getPermissionIds());

    return new ResponseBean(CommonStatus.OK, ResponseMessage.SUCCESS, null);
  }
}