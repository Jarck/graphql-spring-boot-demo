package com.hello.world.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <E>
 * @author jarck-lou
 * @date 2018/9/11 14:04
 */
@MappedTypes({UserStatusEnum.class, RoleStatusEnum.class, CompanyStatusEnum.class, PermissionAvailableEnum.class})
public class CodeEnumTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {
  private Class<E> type;
  private E[] enums;

  public CodeEnumTypeHandler(Class<E> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }

    this.type = type;
    this.enums = type.getEnumConstants();
    if (this.enums == null) {
      throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type");
    }
  }

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType)
          throws SQLException {
    ps.setInt(i, parameter.getCode());
  }

  @Override
  public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
    int i = rs.getInt(columnName);

    if (rs.wasNull()) {
      return null;
    } else {
      return codeOf(i);
    }
  }

  @Override
  public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    int i = rs.getInt(columnIndex);

    if (rs.wasNull()) {
      return null;
    } else {
      return codeOf(i);
    }
  }

  @Override
  public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    int i = cs.getInt(columnIndex);

    if (cs.wasNull()) {
      return null;
    } else {
      return codeOf(i);
    }
  }

  /**
   * 根据code查找枚举
   *
   * @param code code
   * @return enum
   */
  private E codeOf(int code) {
    for (E e : type.getEnumConstants()) {
      if (e.getCode() == code) {
        return e;
      }
    }

    throw new IllegalArgumentException("Cannot convert" + code + " to " + type.getSimpleName());
  }
}
