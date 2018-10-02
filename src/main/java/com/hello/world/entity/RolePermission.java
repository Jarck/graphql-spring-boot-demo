package com.hello.world.entity;

import lombok.Data;

import java.util.Date;

/**
 * 角色权限
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class RolePermission {
  private Long id;
  private Long roleId;
  private Long permissionId;
  private Date createdAt;
  private Date updatedAt;
}
