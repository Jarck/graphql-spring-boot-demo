package hello.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private String name;
  private String phone;
  private Long cityId;
  private Long companyId;
  private Long[] roleIds;
}
