package com.hello.world.service;

import com.hello.world.dto.result.CompanyDto;
import com.hello.world.entity.Company;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.create.CreateCompanyDto;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:36
 **/
public interface ICompanyService {
  /**
   * find by id
   *
   * @param companyId
   * @return
   */
  CompanyDto searchWithId(Long companyId);

  /**
   * 通过公司名查询
   *
   * @param name
   * @return
   */
  List<Company> searchWithName(String name);

  /**
   * 通过城市id查询
   *
   * @param cityId
   * @return
   */
  List<Company> searchWithCityId(Long cityId);

  /**
   * 按条件查询
   *
   * @param searchCompanyDto 搜索条件
   * @return
   */
  List<Company> searchCondition(SearchCompanyDto searchCompanyDto);

  /**
   * 创建公司
   *
   * @param createCompanyDto
   * @return
   */
  Long createCompany(CreateCompanyDto createCompanyDto);
}
