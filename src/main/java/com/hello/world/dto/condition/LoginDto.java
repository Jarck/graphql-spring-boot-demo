package com.hello.world.dto.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
@ApiModel(description = "用户登录")
public class LoginDto {
  @ApiModelProperty(value = "手机号码", required = true, example = "18812345671")
  @NotBlank(message = "请填写手机号码")
  private String phone;

  @ApiModelProperty(value = "验证码", required = true, example = "123456")
  @NotBlank(message = "请填写验证码")
  private String verifyCode;
}
