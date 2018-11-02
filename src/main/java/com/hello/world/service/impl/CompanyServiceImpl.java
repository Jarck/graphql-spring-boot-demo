package com.hello.world.service.impl;

import com.hello.world.dao.CompanyMapper;
import com.hello.world.dto.result.CompanyDto;
import com.hello.world.entity.Company;
import com.hello.world.dto.condition.SearchCompanyDto;
import com.hello.world.dto.create.CreateCompanyDto;
import com.hello.world.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:47
 **/
@Service
public class CompanyServiceImpl implements ICompanyService {
  @Autowired
  private CompanyMapper companyMapper;

  @Override
  public CompanyDto searchWithId(Long cityId) {
    Company company = companyMapper.selectByPrimaryKey(cityId);

    CompanyDto companyDto = null;
    if (company != null) {
      companyDto = new CompanyDto(company);
    }

    return companyDto;
  }

  @Override
  public List<Company> searchWithName(String name) {
    List<Company> companyList = companyMapper.searchWithName(name);

    return companyList;
  }

  @Override
  public List<Company> searchWithCityId(Long cityId) {
    List<Company> companyList = companyMapper.searchWithCityId(cityId);

    return companyList;
  }

  @Override
  public List<Company> searchCondition(SearchCompanyDto searchCompanyDto) {
    List<Company> companyList = companyMapper.searchCondition(searchCompanyDto);

    return companyList;
  }

  @Override
  public Long createCompany(CreateCompanyDto companyDto) {
    return companyMapper.createCompany(companyDto);
  }

}
