package com.hello.world.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @param <T> 泛型
 * @author jarck-lou
 * @date 2018/11/07 10:03
 */
@Data
@AllArgsConstructor
public class ResponseBean<T> {
  private int code;
  private String msg;
  private T data;
}
