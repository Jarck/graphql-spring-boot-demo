package hello.service.impl;

import hello.dao.RoleMapper;
import hello.dto.create.CreateRoleDto;
import hello.entity.Role;
import hello.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 16:13
 **/
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {
  @Autowired
  private RoleMapper roleMapper;

  @Override
  public List<Role> findAll() {
    return roleMapper.findAll();
  }

  @Override
  public Role searchWithId(Long roleId) {
    return roleMapper.selectByPrimaryKey(roleId);
  }

  @Override
  public List<Role> searchWithName(String name) {
    return roleMapper.searchWithName(name);
  }

  @Override
  public List<Role> searchWithUserId(Long userId) {
    return roleMapper.searchWithUserId(userId);
  }

  @Override
  public Integer createRole(CreateRoleDto createRoleDto) {
    return roleMapper.insertRole(createRoleDto);
  }
}
