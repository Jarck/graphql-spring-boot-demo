package com.hello.world.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author jarck-lou
 * @date 01/11/2018 14:04
 **/
public final class ValidatorUtil {
  private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  /**
   * Don't let anyone instantiate this class.
   */
  private ValidatorUtil() {
  }

  /**
   * 校验
   *
   * @param obj obj
   * @param <T> t
   * @return errorMap
   */
  public static <T> Map<String, StringBuffer> validate(T obj) {
    Map<String, StringBuffer> errorMap = null;

    Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
    if (set != null && set.size() > 0) {
      errorMap = new HashMap<String, StringBuffer>();
      String property = null;

      for (ConstraintViolation<T> cv : set) {
        property = cv.getPropertyPath().toString();
        if (errorMap.get(property) != null) {
          errorMap.get(property).append("," + cv.getMessage());
        } else {
          StringBuffer stringBuffer = new StringBuffer();
          stringBuffer.append(cv.getMessage());
          errorMap.put(property, stringBuffer);
        }
      }
    }

    return errorMap;
  }
}
