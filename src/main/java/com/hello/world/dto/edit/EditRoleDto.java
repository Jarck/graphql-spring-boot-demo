package com.hello.world.dto.edit;

import com.hello.world.dto.create.CreateRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author jarck-lou
 * @date 2019/02/01 15:33
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EditRoleDto extends CreateRoleDto {
  private List<Long> permissionIds;
}
