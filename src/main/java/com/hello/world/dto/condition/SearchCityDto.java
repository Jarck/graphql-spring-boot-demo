package com.hello.world.dto.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索城市DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "城市查询条件")
public class SearchCityDto {
  @ApiModelProperty(value = "城市ID")
  private Long id;

  @ApiModelProperty(value = "城市名称", example = "杭州")
  private String name;
}
