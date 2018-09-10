package com.hello.world.dto.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCompanyDto {
  private Long id;
  private String name;
  private Long cityId;
}
