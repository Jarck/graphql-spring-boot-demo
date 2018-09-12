package com.hello.world.enums;

/**
 * @author jarck-lou
 * @date 2018/9/10 17:23
 **/
public enum UserStatusEnum implements BaseEnum {
  ARCHIVED("禁用", 0),
  ACTIVE("正常", 1);

  private String desc;
  private int code;

  UserStatusEnum(String desc, int code) {
    this.desc = desc;
    this.code = code;
  }

  public String getDesc() {
    return this.desc;
  }

  public int getCode() {
    return this.code;
  }

  /**
   * 通过状态获取状态编码
   *
   * @param status
   * @return
   */
  public static int statusOf(String status) {
    switch (status) {
      case "archived":
        return ARCHIVED.getCode();
      case "active":
        return ACTIVE.getCode();
      default:
        return ACTIVE.getCode();
    }
  }

  /**
   * 通过状态编码获取状态说明
   *
   * @param code
   * @return
   */
  public static String codeOf(int code) {
    switch (code) {
      case 0:
        return ARCHIVED.getDesc();
      case 1:
        return ACTIVE.getDesc();
      default:
        return "未知";
    }
  }
}
