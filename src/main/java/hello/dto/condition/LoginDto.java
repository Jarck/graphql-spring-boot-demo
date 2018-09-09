package hello.dto.condition;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class LoginDto {
  @NotBlank(message = "请填写手机号码")
  private String phone;

  @NotBlank(message = "请填写验证码")
  private String verifyCode;
}
