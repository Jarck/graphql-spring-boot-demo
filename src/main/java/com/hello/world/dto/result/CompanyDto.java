package com.hello.world.dto.result;

import com.hello.world.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author jarck-lou
 * @date 2018/9/9 17:37
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto extends Company {

  private CityDto cityDto;
}
