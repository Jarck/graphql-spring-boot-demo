package com.hello.world.dao;

import com.hello.world.dto.result.CompanyDto;
import com.hello.world.entity.Company;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.create.CreateCompanyDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司DAO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Mapper
@Repository
public interface CompanyMapper {
  /**
   * 按ID删除
   *
   * @param id ID
   * @return 影响行数
   */
  int deleteByPrimaryKey(Integer id);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insert(Company record);

  /**
   * 新建
   *
   * @param record record
   * @return 影响行数
   */
  int insertSelective(Company record);

  /**
   * 按ID查询
   *
   * @param id ID
   * @return 公司
   */
  CompanyDto selectByPrimaryKey(Long id);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKeySelective(Company record);

  /**
   * 更新
   *
   * @param record record
   * @return 影响行数
   */
  int updateByPrimaryKey(Company record);

  /**
   * 按公司名查询
   *
   * @param name name
   * @return 公司列表
   */
  List<CompanyDto> searchWithName(String name);

  /**
   * 按城市ID查询
   *
   * @param cityId 城市ID
   * @return 公司列表
   */
  List<CompanyDto> searchWithCityId(Long cityId);

  /**
   * 按条件查询
   *
   * @param searchCompanyDto 查询条件
   * @return 公司列表
   */
  List<CompanyDto> searchCondition(SearchCompanyDto searchCompanyDto);

  /**
   * 创建公司
   *
   * @param createCompanyDto 查询公司
   * @return 影响的行数
   */
  Long createCompany(CreateCompanyDto createCompanyDto);
}
