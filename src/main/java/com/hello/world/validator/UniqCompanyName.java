package com.hello.world.validator;

import com.hello.world.service.ICompanyService;
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
 * @date 2019/02/18 17:53
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqCompanyName.UniqCompanyNameValidator.class})
public @interface UniqCompanyName {
  String message() default "公司名称已存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 公司名称唯一性校验
   */
  class UniqCompanyNameValidator implements ConstraintValidator<UniqCompanyName, String> {
    @Autowired
    private ICompanyService companyService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (StringUtils.isBlank(value)) {
        return true;
      }

      if (companyService.exitsCompanyName(value)) {
        return false;
      }

      return true;
    }
  }
}
