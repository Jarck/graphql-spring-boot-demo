package com.hello.world.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色权限
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class RolePermission implements Serializable {
  private static final long serialVersionUID = 8258965897814256145L;

  private Long id;
  private Long roleId;
  private Long permissionId;
  private Date createdAt;
  private Date updatedAt;
}
