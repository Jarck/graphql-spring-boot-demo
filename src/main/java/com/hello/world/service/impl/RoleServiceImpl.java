package com.hello.world.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hello.world.dao.RoleMapper;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.entity.Role;
import com.hello.world.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 16:13
 **/
@Service
public class RoleServiceImpl implements IRoleService {
  @Autowired
  private RoleMapper roleMapper;

  @Override
  public List<Role> findAll() {
    return roleMapper.findAll();
  }

  @Override
  public PageInfo<Role> findAll(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
    PageHelper.orderBy(pageDto.getOrderBy() + " " + (pageDto.isDesc() ? "desc" : "asc"));

    List<Role> roleList = roleMapper.findAll();
    PageInfo<Role> rolePageInfo = new PageInfo<>(roleList);

    return rolePageInfo;
  }

  @Override
  public Role searchWithId(Long roleId) {
    Role role = roleMapper.selectByPrimaryKey(roleId);
    return role;
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
  public Long createRole(CreateRoleDto createRoleDto) {
    return roleMapper.insertRole(createRoleDto);
  }
}
