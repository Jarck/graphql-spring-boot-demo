package com.hello.world.service.impl;

import com.hello.world.dao.PermissionMapper;
import com.hello.world.dao.RoleMapper;
import com.hello.world.dao.RolePermissionMapper;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.service.IRolePermissionsService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jarck-lou
 * @date 2019/02/01 16:03
 **/
@Service
public class RolePermissionsServiceImpl implements IRolePermissionsService {

  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private PermissionMapper permissionMapper;

  @Autowired
  private RolePermissionMapper rolePermissionMapper;

  @Override
  @Transactional
  public int updateRolePermissions(Long roleId, List<Long> permissionIds) throws NotFoundException {
    RoleDto roleDto = roleMapper.selectByPrimaryKey(roleId);

    if (roleDto == null) {
      throw new NotFoundException("角色不存在");
    }

    int i;
    if (permissionIds.size() == 0) {
      i = rolePermissionMapper.deleteByRoleId(roleId);
    } else {
      List<Long> existPermissionList = permissionMapper.findAll()
              .stream()
              .map(it -> it.getId())
              .collect(Collectors.toList());

      boolean validPermission = existPermissionList.containsAll(permissionIds);

      if (!validPermission) {
        throw new NotFoundException("权限不存在");
      }

      i = rolePermissionMapper.createRolePermissions(roleId, permissionIds);
    }

    return i;
  }
}
