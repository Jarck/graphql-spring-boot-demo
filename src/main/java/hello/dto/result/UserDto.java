package hello.dto.result;

import hello.entity.Permission;
import hello.entity.Role;
import hello.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto extends User implements Serializable {
    private List<Role> roles = new ArrayList<>();
    private List<Permission> permissions = new ArrayList<>();
    private String secretKey;

    public UserDto(){
    }

    public UserDto(User user){
        this.setId(user.getId());
        this.setName(user.getName());
        this.setCityId(user.getCityId());
        this.setCompanyId(user.getCompanyId());;
        this.setPhone(user.getPhone());
        this.setStatus(user.getStatus());
        this.setCreatedAt(user.getCreatedAt());
        this.setUpdatedAt(user.getUpdatedAt());
    }
}
