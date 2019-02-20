package com.hello.world.validator;

import com.hello.world.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jarck-lou
 * @date 2019/02/18 17:10
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqUserPhone.UniqUserPhoneValidator.class})
public @interface UniqUserPhone {
  String message() default "手机号码已存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 用户手机号码唯一性校验
   */
  class UniqUserPhoneValidator implements ConstraintValidator<UniqUserPhone, String> {
    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (StringUtils.isBlank(value)) {
        return true;
      }

      if (userService.getUserByPhone(value) != null) {
        return false;
      }

      return true;
    }
  }
}
