package com.hello.world.entity;

import lombok.Data;

import java.util.Date;

/**
 * 城市
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class City {
  private Long id;
  private String name;
  private Date createdAt;
  private Date updatedAt;
}
