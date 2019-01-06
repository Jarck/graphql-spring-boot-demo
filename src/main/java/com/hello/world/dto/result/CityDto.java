package com.hello.world.dto.result;

import com.hello.world.entity.City;
import lombok.Data;

import java.io.Serializable;

/**
 * 城市DTO
 *
 * @author jarck-lou
 * @date 2018/9/1 12:52
 **/
@Data
public class CityDto extends City implements Serializable {
  private static final long serialVersionUID = -2667574691388311648L;

  public CityDto() {

  }

  public CityDto(City city) {
    if (city != null) {
      this.setId(city.getId());
      this.setName(city.getName());
      this.setCreatedAt(city.getCreatedAt());
      this.setUpdatedAt(city.getUpdatedAt());
    }
  }
}
