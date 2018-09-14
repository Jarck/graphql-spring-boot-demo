package com.hello.world.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDto {
  private Long id;
  private String name;
  private String shortName;
  private String address;
  private String linkName;
  private String phone;
  private Long cityId;
}
