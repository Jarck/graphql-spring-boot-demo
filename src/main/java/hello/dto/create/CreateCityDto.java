package hello.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建城市DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCityDto {
  private String name;
}
