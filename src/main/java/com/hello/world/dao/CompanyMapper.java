package com.hello.world.dao;

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
  int deleteByPrimaryKey(Integer id);

  int insert(Company record);

  int insertSelective(Company record);

  Company selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(Company record);

  int updateByPrimaryKey(Company record);

  /**
   * 按公司名查询
   *
   * @param name
   * @return
   */
  List<Company> searchWithName(String name);

  /**
   * 按城市ID查询
   *
   * @param cityId
   * @return
   */
  List<Company> searchWithCityId(Long cityId);

  /**
   * 按条件查询
   *
   * @param searchCompanyDto
   * @return
   */
  List<Company> searchCondition(SearchCompanyDto searchCompanyDto);

  /**
   * 创建公司
   *
   * @param createCompanyDto
   * @return 影响的行数
   */
  Long createCompany(CreateCompanyDto createCompanyDto);
}