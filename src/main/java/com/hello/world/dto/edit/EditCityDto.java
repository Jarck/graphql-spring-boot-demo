package com.hello.world.dto.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author jarck-lou
 * @date 2019/02/12 10:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class EditCityDto {
  @ApiModelProperty(value = "城市ID", required = true)
  private Long id;

  @ApiModelProperty(value = "城市名称", required = true)
  @NotBlank(message = "城市名不能为空")
  private String name;
}
