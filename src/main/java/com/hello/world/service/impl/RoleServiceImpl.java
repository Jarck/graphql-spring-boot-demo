package com.hello.world.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hello.world.dao.RoleMapper;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.create.CreateRoleDto;
import com.hello.world.dto.result.RoleDto;
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
  public List<RoleDto> searchWithName(String name) {
    return roleMapper.searchWithName(name);
  }

  @Override
  public List<RoleDto> searchWithUserId(Long userId) {
    return roleMapper.searchWithUserId(userId);
  }

  @Override
  public Long createRole(CreateRoleDto createRoleDto) {
    return roleMapper.insertRole(createRoleDto);
  }
}
