package com.hello.world.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class City implements Serializable {
  private static final long serialVersionUID = 4410555123881138923L;

  private Long id;
  private String name;
  private Date createdAt;
  private Date updatedAt;
}
