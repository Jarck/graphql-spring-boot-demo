package com.hello.world.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户角色
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class UserRole {
  private Long id;
  private Long userId;
  private Long roleId;
  private Date createdAt;
  private Date updatedAt;
}
