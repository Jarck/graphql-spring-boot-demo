package com.hello.world.dto.result;

import com.hello.world.entity.Permission;
import com.hello.world.entity.Role;
import com.hello.world.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class UserDto extends User implements Serializable {
  private static final long serialVersionUID = 4552788051779886957L;

  private String secretKey;

  private List<Role> roles = new ArrayList<>();
  private List<Permission> permissions = new ArrayList<>();

  public UserDto() {

  }

  public UserDto(User user) {
    if (user != null) {
      this.setId(user.getId());
      this.setName(user.getName());
      this.setCityId(user.getCityId());
      this.setCompanyId(user.getCompanyId());
      this.setPhone(user.getPhone());
      this.setStatus(user.getStatus());
      this.setCreatedAt(user.getCreatedAt());
      this.setUpdatedAt(user.getUpdatedAt());
    }
  }
}
