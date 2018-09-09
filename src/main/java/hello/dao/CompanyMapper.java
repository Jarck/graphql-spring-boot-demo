package hello.dao;

import hello.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

  Company selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(Company record);

  int updateByPrimaryKey(Company record);
}