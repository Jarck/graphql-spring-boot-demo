package com.hello.world.dto.create;

import com.hello.world.validator.UniqCityName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 创建城市DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "城市")
public class CreateCityDto {
  @ApiModelProperty(hidden = true)
  private Long id;

  @ApiModelProperty(value = "城市名称", required = true, example = "杭州")
  @NotBlank(message = "城市名称不能为空")
  @UniqCityName
  private String name;
}
