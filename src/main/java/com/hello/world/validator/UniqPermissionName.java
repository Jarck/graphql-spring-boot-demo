package com.hello.world.validator;

import com.hello.world.service.IPermissionService;
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
 * @date 2019/02/19 09:31
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqPermissionName.UniqPermissionNameValidator.class)
public @interface UniqPermissionName {
  String message() default "权限名称已存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 权限名称唯一性校验
   */
  class UniqPermissionNameValidator implements ConstraintValidator<UniqPermissionName, String> {
    @Autowired
    private IPermissionService permissionService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (StringUtils.isBlank(value)) {
        return true;
      }

      if (permissionService.exitsPermissionName(value)) {
        return false;
      }

      return true;
    }
  }
}
