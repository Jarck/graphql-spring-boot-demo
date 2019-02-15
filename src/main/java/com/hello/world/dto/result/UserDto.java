package com.hello.world.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hello.world.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends User {

  @JsonIgnore
  private String secretKey;

  private CityDto cityDto;
  private CompanyDto companyDto;
  private List<RoleDto> roles;
}
