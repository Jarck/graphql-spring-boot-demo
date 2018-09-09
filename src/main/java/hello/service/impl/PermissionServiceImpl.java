package hello.service.impl;

import hello.dao.PermissionMapper;
import hello.dto.result.PermissionDto;
import hello.entity.Permission;
import hello.service.IPermissionService;
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
    Permission permission = permissionMapper.selectByPrimaryKey(permissionId);

    PermissionDto permissionDto = new PermissionDto();
    if (permission != null) {
      permissionDto = new PermissionDto(permission);
    }

    return permissionDto;
  }

  @Override
  public List<Permission> searchWithName(String name) {
    List<Permission> permissionList = permissionMapper.searchWithName(name);

    return permissionList;
  }

  @Override
  public List<Permission> searchWithRoleId(Long roleId) {
    List<Permission> permissionList = permissionMapper.searchWithRoleId(roleId);

    return permissionList;
  }

  @Override
  public List<Permission> searchWithUserId(Long userId) {
    List<Permission> permissionList = permissionMapper.searchWithUserId(userId);

    return permissionList;
  }
}
