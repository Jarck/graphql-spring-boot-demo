package com.hello.world.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 创建用户DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
  private Long id;

  @NotBlank(message = "姓名不能为空")
  private String name;

  @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式错误")
  @NotBlank(message = "手机号不能为空")
  private String phone;

  private Long cityId;

  private Long companyId;

  private List<Long> roleIds;
}
