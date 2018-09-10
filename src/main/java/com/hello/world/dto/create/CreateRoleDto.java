package com.hello.world.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jarck-lou
 * @date 2018/9/9 14:20
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleDto {
  String name;
  String remark;
}
