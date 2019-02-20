package com.hello.world.validator;

import com.hello.world.service.IRoleService;
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
 * @date 2019/02/18 17:45
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqRoleName.UniqRoleNameVaildator.class})
public @interface UniqRoleName {
  String message() default "角色名称已存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  /**
   * 角色名称唯一性校验
   */
  class UniqRoleNameVaildator implements ConstraintValidator<UniqRoleName, String> {
    @Autowired
    private IRoleService roleService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (StringUtils.isBlank(value)) {
        return true;
      }

      if (roleService.exitsRoleName(value)) {
        return false;
      }

      return true;
    }
  }
}
