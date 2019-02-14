package com.hello.world.dto.edit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author jarck-lou
 * @date 2019/02/12 15:49
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class EditCompanyDto {
  @ApiModelProperty(value = "公司ID", required = true)
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
