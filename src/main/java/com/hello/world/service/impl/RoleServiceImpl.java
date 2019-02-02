package com.hello.world.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hello.world.dao.RoleMapper;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.edit.EditRoleDto;
import com.hello.world.dto.result.RoleDto;
import com.hello.world.exception.ArgumentsException;
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
  public List<RoleDto> findAll() {
    return roleMapper.findAll();
  }

  @Override
  public PageInfo<RoleDto> findAll(PageDto pageDto) {
    PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
    PageHelper.orderBy(pageDto.getOrderBy() + " " + (pageDto.isDesc() ? "desc" : "asc"));

    List<RoleDto> roleList = roleMapper.findAll();
    PageInfo<RoleDto> rolePageInfo = new PageInfo<>(roleList);

    return rolePageInfo;
  }

  @Override
  public RoleDto searchWithId(Long roleId) {
    RoleDto roleDto = roleMapper.selectByPrimaryKey(roleId);
    return roleDto;
  }

  @Override
  public RoleDto searchRoleAndPermissions(Long roleId) {
    return roleMapper.searchRoleAndPermissions(roleId);
  }

  @Override
  public List<RoleDto> searchWithName(String name) {
    return roleMapper.searchWithName(name);
  }

  @Override
  public List<RoleDto> searchWithUserId(Long userId) {
    return roleMapper.searchWithUserId(userId);
  }

  @Override
  public RoleDto createRole(CreateRoleDto createRoleDto) throws ArgumentsException {
    List<RoleDto> roleList = roleMapper.searchWithName(createRoleDto.getName());

    if (roleList != null && roleList.size() != 0) {
      throw new ArgumentsException("角色已存在");
    }

    roleMapper.insertRole(createRoleDto);
    return roleMapper.selectByPrimaryKey(createRoleDto.getId());
  }

  @Override
  public RoleDto updateRole(EditRoleDto editRoleDto) {
    roleMapper.update(editRoleDto);

    return roleMapper.selectByPrimaryKey(editRoleDto.getId());
  }
}
