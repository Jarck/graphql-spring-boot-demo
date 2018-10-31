package com.hello.world.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyDto {
  private Long id;
  @NotBlank(message = "公司名不能为空")
  private String name;
  @NotBlank(message = "公司简称不能为空")
  private String shortName;
  private String address;
  private String linkName;
  private String phone;
  private Long cityId;
}
