package com.hello.world.dto.result;

import com.hello.world.entity.Permission;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jarck-lou
 * @date 2018/9/9 20:16
 **/
@Data
public class PermissionDto extends Permission implements Serializable {
  public PermissionDto() {

  }

  public PermissionDto(Permission permission) {
    this.setId(permission.getId());
    this.setName(permission.getName());
    this.setPermission(permission.getPermission());
    this.setResourceType(permission.getResourceType());
    this.setAvailable(permission.getAvailable());
    this.setCreatedAt(permission.getCreatedAt());
    this.setUpdatedAt(permission.getUpdatedAt());
  }
}
