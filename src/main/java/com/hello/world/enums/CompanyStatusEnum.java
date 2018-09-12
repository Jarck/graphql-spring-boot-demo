package com.hello.world.enums;

/**
 * @author jarck-lou
 * @date 2018/9/11 21:35
 **/
public enum CompanyStatusEnum implements BaseEnum {
  ARCHIVED("禁用", 0),
  ACTIVE("正常", 1);

  private String desc;
  private int code;

  CompanyStatusEnum(String desc, int code) {
    this.desc = desc;
    this.code = code;
  }

  public String getDesc() {
    return this.desc;
  }

  public int getCode() {
    return this.code;
  }
}
