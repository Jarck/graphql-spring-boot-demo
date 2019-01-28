package com.hello.world.service;

import com.github.pagehelper.PageInfo;
import com.hello.world.dto.PageDto;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.create.CreateCompanyDto;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:36
 **/
public interface ICompanyService {
  /**
   * 通过ID查询
   *
   * @param companyId 公司ID
   * @return 公司
   */
  CompanyDto searchWithId(Long companyId);

  /**
   * 通过ID查询
   *
   * @param companyId 公司ID
   * @return 公司
   */
  CompanyDto searchCompanyAndCityWithId(Long companyId);

  /**
   * 通过公司名查询
   *
   * @param name 公司名
   * @return 公司列表
   */
  List<CompanyDto> searchWithName(String name);

  /**
   * 通过城市id查询
   *
   * @param cityId 城市ID
   * @return 公司列表
   */
  List<CompanyDto> searchWithCityId(Long cityId);

  /**
   * 按条件查询
   *
   * @param searchCompanyDto 搜索条件
   * @return 公司列表
   */
  List<CompanyDto> searchCondition(SearchCompanyDto searchCompanyDto);

  /**
   * 分页查询
   *
   * @param searchCompanyDto 搜索条件
   * @param pageDto 分页信息
   * @return 公司page
   */
  PageInfo<CompanyDto> searchCondition(SearchCompanyDto searchCompanyDto, PageDto pageDto);

  /**
   * 创建公司
   *
   * @param createCompanyDto 公司
   * @return 影响行数
   */
  Long createCompany(CreateCompanyDto createCompanyDto);
}
