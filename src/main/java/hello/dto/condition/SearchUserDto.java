package hello.dto.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索用户DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserDto {
  private Long id;
  private String name;
  private String phone;
  private Long roleId;
  private Long cityId;
  private Long companyId;
  private Integer status;
}
