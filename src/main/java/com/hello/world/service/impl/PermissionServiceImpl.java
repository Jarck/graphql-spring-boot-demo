package com.hello.world.service.impl;

import com.hello.world.dao.PermissionMapper;
import com.hello.world.dto.create.CreatePermissionDto;
import com.hello.world.dto.result.PermissionDto;
import com.hello.world.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 20:24
 **/
@Service
public class PermissionServiceImpl implements IPermissionService {
  @Autowired
  private PermissionMapper permissionMapper;

  @Override
  public PermissionDto searchWithId(Long permissionId) {
    PermissionDto permissionDto = permissionMapper.selectByPrimaryKey(permissionId);

    return permissionDto;
  }

  @Override
  public List<PermissionDto> searchWithName(String name) {
    List<PermissionDto> permissionList = permissionMapper.searchWithName(name);

    return permissionList;
  }

  @Override
  public List<PermissionDto> searchWithRoleId(Long roleId) {
    List<PermissionDto> permissionList = permissionMapper.searchWithRoleId(roleId);

    return permissionList;
  }

  @Override
  public List<PermissionDto> searchWithUserId(Long userId) {
    List<PermissionDto> permissionList = permissionMapper.searchWithUserId(userId);

    return permissionList;
  }

  @Override
  public Long insertPermission(CreatePermissionDto createPermissionDto) {
    return permissionMapper.insertPermission(createPermissionDto);
  }
}
