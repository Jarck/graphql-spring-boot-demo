package com.hello.world.dto.create;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "公司")
public class CreateCompanyDto {
  @ApiModelProperty(hidden = true)
  private Long id;

  @ApiModelProperty(value = "公司名称", required = true, example = "杭州xxx有限公司")
  @NotBlank(message = "公司名称不能为空")
  private String name;

  @ApiModelProperty(value = "公司简称", required = true, example = "杭州xxx")
  @NotBlank(message = "公司简称不能为空")
  private String shortName;

  @ApiModelProperty(value = "公司地址")
  private String address;

  @ApiModelProperty(value = "公司联系人")
  private String linkName;

  @ApiModelProperty(value = "联系号码")
  private String phone;

  @ApiModelProperty(value = "城市ID")
  private Long cityId;
}
