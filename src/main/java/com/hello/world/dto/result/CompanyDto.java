package com.hello.world.dto.result;

import com.hello.world.entity.Company;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:37
 **/
@Data
public class CompanyDto extends Company implements Serializable {
  private static final long serialVersionUID = -5501876195207706778L;

  public CompanyDto() {

  }

  public CompanyDto(Company company) {
    if (company != null) {
      this.setId(company.getId());
      this.setName(company.getName());
      this.setShortName(company.getShortName());
      this.setAddress(company.getAddress());
      this.setLinkName(company.getLinkName());
      this.setPhone(company.getPhone());
      this.setCityId(company.getCityId());
      this.setStatus(company.getStatus());
      this.setCreatedAt(company.getCreatedAt());
      this.setUpdatedAt(company.getUpdatedAt());
    }
  }
}
