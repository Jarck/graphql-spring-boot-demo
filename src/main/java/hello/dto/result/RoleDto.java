package hello.dto.result;

import hello.entity.Permission;
import hello.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class RoleDto extends Role implements Serializable {
  private List<Permission> permissions = new ArrayList<>();

  public RoleDto() {

  }

  public RoleDto(Role role) {
    this.setId(role.getId());
    this.setName(role.getName());
    this.setStatus(role.getStatus());
    this.setRemark(role.getRemark());
    this.setCreatedAt(role.getCreatedAt());
    this.setUpdatedAt(role.getUpdatedAt());
  }
}
