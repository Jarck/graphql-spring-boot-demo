package com.hello.world.validator;

import com.hello.world.service.ICityService;
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
 * @date 2019/02/18 17:33
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqCityName.UniqCityNameValidator.class})
public @interface UniqCityName {
  String message() default "城市名称已存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 城市名称唯一性校验
   */
  class UniqCityNameValidator implements ConstraintValidator<UniqCityName, String> {
    @Autowired
    private ICityService cityService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (StringUtils.isBlank(value)) {
        return true;
      }

      if (cityService.exitsCityName(value)) {
        return false;
      }

      return true;
    }
  }
}
