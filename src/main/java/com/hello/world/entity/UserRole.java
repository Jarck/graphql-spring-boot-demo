package com.hello.world.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class UserRole implements Serializable {
  private static final long serialVersionUID = 8205152166247696160L;

  private Long id;
  private Long userId;
  private Long roleId;
  private Date createdAt;
  private Date updatedAt;
}
