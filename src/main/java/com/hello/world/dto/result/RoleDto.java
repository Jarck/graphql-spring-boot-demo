package com.hello.world.dto.result;

import com.hello.world.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RoleDto extends Role {
}
