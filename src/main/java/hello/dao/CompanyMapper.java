package hello.dao;

import hello.dto.condition.SearchCompanyDto;
import hello.dto.create.CreateCompanyDto;
import hello.entity.Company;
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

  List<Company> searchWithName(String name);

  List<Company> searchWithCityId(Long cityId);

  List<Company> searchCondition(SearchCompanyDto searchCompanyDto);

  Integer createCompany(CreateCompanyDto createCompanyDto);
}