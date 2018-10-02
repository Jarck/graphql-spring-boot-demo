package com.hello.world.entity;

import com.hello.world.enums.RoleStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 角色
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class Role {
  private Long id;
  private String name;
  private RoleStatusEnum status;
  private String remark;
  private Date createdAt;
  private Date updatedAt;
}
