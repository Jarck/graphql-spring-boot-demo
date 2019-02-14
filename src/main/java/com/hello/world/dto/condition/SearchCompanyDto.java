package com.hello.world.dto.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "公司搜索条件")
public class SearchCompanyDto {
  @ApiModelProperty(value = "公司ID")
  private Long id;

  @ApiModelProperty(value = "公司名称")
  private String name;

  @ApiModelProperty(value = "城市ID")
  private Long cityId;
}
