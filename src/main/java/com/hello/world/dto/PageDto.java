package com.hello.world.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author jarck-lou
 * @date 2018/11/05 10:38
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
  private static final Integer DEFAULT_PAGE_SIZE = 20;

  private Integer pageNum;
  private Integer pageSize;
  private String orderBy;
  private Boolean desc;

  /**
   * 默认获取第1页
   *
   * @return pageNum
   */
  public Integer getPageNum() {
    if (pageNum == null) {
      return 1;
    }

    return pageNum;
  }

  /**
   * 默认每页20条
   *
   * @return pageSize
   */
  public Integer getPageSize() {
    if (pageSize == null) {
      return DEFAULT_PAGE_SIZE;
    }

    return pageSize;
  }

  /**
   * 默认按id排序
   *
   * @return orderBy
   */
  public String getOrderBy() {
    if (StringUtils.isBlank(orderBy)) {
      return "id";
    }

    return orderBy;
  }

  /**
   * 默认倒序排序
   *
   * @return desc
   */
  public Boolean isDesc() {
    return desc == null || desc;
  }
}
