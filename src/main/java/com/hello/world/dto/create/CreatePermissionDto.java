package com.hello.world.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jarck-lou
 * @date 2018/9/9 14:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePermissionDto {
  private Long id;
  private String name;
  private String permission;
  private String resourceType;
}
